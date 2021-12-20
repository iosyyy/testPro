package com.test.controller;

import com.google.common.base.Preconditions;
import com.test.annotation.CacheRemove;
import com.test.dao.CartRepository;
import com.test.dao.HeaRepository;
import com.test.dao.UserInRepository;
import com.test.entry.Cart;
import com.test.entry.Hea;
import com.test.entry.UserIn;
import com.test.proper.TencentOssProperties;
import com.test.utils.RETURNCODE;
import com.test.utils.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author authoa
 * @since 2021/9/23
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class TestCons {
  @Resource CacheManager cacheManager;
  @Resource UserInRepository userInRepository;
  @Resource CartRepository cartRepository;
  @Resource HeaRepository heaRepository;
  @Resource TencentOssProperties tencentOssProperties;

  @GetMapping("/getAllHea")
  public ReturnResult getAllHea(
      @RequestParam("page") Integer page,
      @PageableDefault(value = 20, size = 20) Pageable pageable) {

    Preconditions.checkArgument(page != null, "parameter page is null");

    pageable.withPage(page);
    Page<Hea> all = heaRepository.findAll(pageable);
    long count = heaRepository.count();
    Map<String, Object> dataMap =
        new HashMap<>() {
          {
            put("count", count);
            put("hea", all);
          }
        };
    return ReturnResult.builder()
        .code(RETURNCODE.SUCCESS.getCode())
        .msg(RETURNCODE.SUCCESS.getMessage())
        .data(dataMap)
        .build();
  }

  @PostMapping("/setAdmin")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ReturnResult setAdmin(@RequestBody Map<String, Object> ids) {
    Long id = Long.valueOf((Integer) ids.get("id"));
    Boolean admin = (Boolean) ids.get("admin");
    Preconditions.checkArgument(id != null, "parameter id is null");
    Preconditions.checkArgument(admin != null, "parameter cold is null");

    Optional<UserIn> byId = userInRepository.findById(id);
    if (byId.isPresent()) {
      UserIn user = byId.get();
      user.setAdmin(admin);
      userInRepository.save(user);
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(
              new HashMap<>() {
                {
                  put("admin", true);
                }
              })
          .build();
    } else {
      return ReturnResult.builder()
          .code(RETURNCODE.SERVER_ERROR.getCode())
          .msg(RETURNCODE.SERVER_ERROR.getMessage())
          .data(null)
          .build();
    }
  }

  @PostMapping("/changeUser")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ReturnResult changeUser(@RequestBody UserIn user) {
    Preconditions.checkArgument(user.getId() != null, "parameter id is null");
    Preconditions.checkArgument(user.getUserName() != null, "parameter username is null");
    Preconditions.checkArgument(user.getPassWord() != null, "parameter password is null");
    String password = DigestUtils.sha256Hex(user.getPassWord());
    Optional<UserIn> byId = userInRepository.findById(user.getId());
    System.out.println(user);
    if (byId.isPresent()) {
      UserIn user1 = byId.get();
      user1.setPassWord(password);
      user1.setUserName(user.getUserName());
      userInRepository.save(user1);
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(null)
          .build();
    }
    return ReturnResult.builder()
        .code(RETURNCODE.SERVER_ERROR.getCode())
        .msg(RETURNCODE.SERVER_ERROR.getMessage())
        .data(null)
        .build();
  }

  @PostMapping("/setCold")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ReturnResult setCold(@RequestBody Map<String, Object> ids) {
    Long id = Long.valueOf((Integer) ids.get("id"));
    Boolean cold = (Boolean) ids.get("cold");
    Preconditions.checkArgument(id != null, "parameter id is null");
    Preconditions.checkArgument(cold != null, "parameter cold is null");

    Optional<UserIn> byId = userInRepository.findById(id);
    if (byId.isPresent()) {
      UserIn user = byId.get();
      user.setCold(cold);
      userInRepository.save(user);
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(
              new HashMap<>() {
                {
                  put("admin", true);
                }
              })
          .build();
    } else {
      return ReturnResult.builder()
          .code(RETURNCODE.SERVER_ERROR.getCode())
          .msg(RETURNCODE.SERVER_ERROR.getMessage())
          .data(null)
          .build();
    }
  }

  @PostMapping("/showUsers")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ReturnResult getUsers() {

    List<Map<Object, Object>> allUser = userInRepository.findAllUser();
    System.out.println(allUser);
    return ReturnResult.builder()
        .code(RETURNCODE.SUCCESS.getCode())
        .msg(RETURNCODE.SUCCESS.getMessage())
        .data(
            new HashMap<>() {
              {
                put("user", allUser);
              }
            })
        .build();
  }

  @GetMapping("/getAllCart")
  public ReturnResult getAllCart() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = authentication.getName();
    Long id = Long.valueOf(name);

    Optional<UserIn> userin = userInRepository.findById(id);
    Map<String, Object> dataMap = new HashMap<>();

    if (userin.isPresent()) {
      UserIn user = userin.get();
      Cart cart = user.getCart();
      Cart userCart = cart;
      if (cart == null) {
        user.setCart(new Cart());
        UserIn user1 = userInRepository.saveAndFlush(user);
        userCart = user1.getCart();
      }
      List<Hea> heas = userCart.getHeas();
      if (heas != null) {
        dataMap.put("cart", heas);
      } else {
        dataMap.put("cart", null);
      }
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(dataMap)
          .build();
    } else {

      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(dataMap)
          .build();
    }
  }

  @PostMapping("/insertHea")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ReturnResult insertHea(
      @RequestPart("file") MultipartFile file,
      @RequestPart("factory") String factory,
      @RequestPart("price") String price,
      @RequestPart("stock") String stock,
      @RequestPart("describe") String describe,
      @RequestPart("name") String name,
      @RequestPart("updatedAt") String updatedAt)
      throws Exception {
    String file_path = tencentOssProperties.uploadFile(file);
    Hea hea =
        Hea.builder()
            .name(name)
            .factory(factory)
            .updatedAt(new Date(updatedAt))
            .stock(Integer.valueOf(stock))
            .describe(describe)
            .price(Long.valueOf(price))
            .createdAt(new Date(updatedAt))
            .imageUrl(file_path)
            .build();
    Hea save = heaRepository.save(hea);
    return ReturnResult.builder()
        .code(RETURNCODE.SUCCESS.getCode())
        .msg(RETURNCODE.SUCCESS.getMessage())
        .data(
            new HashMap<>() {
              {
                put("hea", save);
              }
            })
        .build();
  }

  @PostMapping("/updateHea")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ReturnResult updateHea(
      @RequestPart("file") MultipartFile file,
      @RequestPart("factory") String factory,
      @RequestPart("price") String price,
      @RequestPart("stock") String stock,
      @RequestPart("describe") String describe,
      @RequestPart("name") String name,
      @RequestPart("id") String id,
      @RequestPart("updatedAt") String updatedAt)
      throws Exception {
    System.out.println(file.getOriginalFilename());
    String file_path = null;
    if (!Objects.equals(file.getOriginalFilename(), "null")) {
      file_path = tencentOssProperties.uploadFile(file);
    }
    Optional<Hea> byId = heaRepository.findById(Long.valueOf(id));
    if (byId.isPresent()) {
      Hea hea1 = byId.get();
      hea1.setName(name);
      hea1.setFactory(factory);
      hea1.setUpdatedAt(new Date(updatedAt));
      hea1.setPrice(Long.valueOf(price));
      hea1.setFactory(factory);
      hea1.setStock(Integer.valueOf(stock));
      hea1.setDescribe(describe);
      if (file_path != null) {
        hea1.setImageUrl(file_path);
      }

      heaRepository.save(hea1);
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .build();
    } else {
      return ReturnResult.builder()
          .code(RETURNCODE.SERVER_ERROR.getCode())
          .msg(RETURNCODE.SERVER_ERROR.getMessage())
          .build();
    }
  }

  @PostMapping("/deleteHea")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ReturnResult deleteHea(@RequestBody Map<String, Object> ids) {
    Integer id = (Integer) ids.get("id");
    Preconditions.checkArgument(id != null, "id不能为null");
    heaRepository.deleteById(Long.valueOf(id));
    return ReturnResult.builder()
        .code(RETURNCODE.SUCCESS.getCode())
        .msg(RETURNCODE.SUCCESS.getMessage())
        .build();
  }

  @PostMapping("/insertCart")
  public ReturnResult insertCart(@RequestBody Map<String, Object> map) {
    Integer id1 = (Integer) map.get("id");
    Preconditions.checkArgument(id1 != null, "id不能为null");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = authentication.getName();
    Long id = Long.valueOf(name);
    Optional<UserIn> user = userInRepository.findById(id);
    boolean present = user.isPresent();
    if (present) {
      UserIn user1 = user.get();
      if (user1.getCart() == null) {
        Cart build =
            Cart.builder()
                .heas(new ArrayList<>())
                .createdAt(new Date())
                .updatedAt(new Date())
                .user(user1)
                .build();
        Cart save = cartRepository.saveAndFlush(build);

        user1.setCart(save);
      }
      Cart cart = user1.getCart();
      if (cart.getHeas() == null) {

        cart.setHeas(new ArrayList<>());
      }

      List<Hea> heas = cart.getHeas();
      Hea hea;
      hea = Hea.builder().id(Long.valueOf(id1)).build();
      heas.add(hea);
      userInRepository.save(user1);
      return ReturnResult.builder()
          .code(RETURNCODE.SUCCESS.getCode())
          .msg(RETURNCODE.SUCCESS.getMessage())
          .data(null)
          .build();
    }
    return ReturnResult.builder()
        .code(RETURNCODE.SERVER_ERROR.getCode())
        .msg(RETURNCODE.SERVER_ERROR.getMessage())
        .data(null)
        .build();
  }

  @GetMapping("/listAll")
  @PreAuthorize("hasAnyRole('ADMIN')")
  @Cacheable(value = "list", key = "#p0+'.'+ #p1", sync = true, cacheManager = "cacheManager")
  public List<Integer> getAll(Long length, Long id) {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      list.add(i);
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return list;
  }

  @GetMapping("/lists")
  public Boolean getAlls(Long length) {
    System.out.println(cacheManager.getClass().getSimpleName());
    Collection<String> cacheNames = cacheManager.getCacheNames();
    for (String cacheName : cacheNames) {
      log.info(cacheName);
    }
    Cache list1 = cacheManager.getCache("list");
    //    list1.evict(length);
    //    list1.evictIfPresent(length);
    if (!Objects.isNull(list1)) {
      return list1.evictIfPresent(length);
    }
    return false;
  }

  @GetMapping("/getUser")
  public ReturnResult getUser() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ReturnResult.builder()
        .code(RETURNCODE.SUCCESS.getCode())
        .msg(RETURNCODE.SUCCESS.getMessage())
        .data(
            new HashMap<>() {
              {
                put("user", authentication.getAuthorities());
              }
            })
        .build();
  }

  /*  @GetMapping("/deleteAll")
  @CacheEvict(value = "list", key = "#p0+'.'+ #p1")
  public void deleteAll(Long length, Long id) {
    System.out.println("every thing has been delete");
  }*/

  @GetMapping("/deleteAll")
  @CacheRemove(
      value = "list",
      key = {})
  public void deleteAll() {
    System.out.println("every thing has been delete");
    int a = 0 / 0;
  }
}
