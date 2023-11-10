package com.example.service.userservice.controller;

import com.example.service.userservice.dto.*;
import com.example.service.userservice.exception.NotFoundException;
import com.example.service.userservice.security.CheckSecurity;
import com.example.service.userservice.service.AdminService;
import com.example.service.userservice.service.ManagerService;
import com.example.service.userservice.service.RankService;
import com.example.service.userservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
@RestController
@RequestMapping
public class Controller {
    private final UserService userService;
    private final AdminService adminService;
    private final ManagerService managerService;

    private final RankService rankService;
    public Controller(UserService userService, AdminService adminService, ManagerService managerService, RankService rankService) {
        this.userService = userService;
        this.adminService = adminService;
        this.managerService = managerService;
        this.rankService=rankService;
    }

    //ADMIN
    @PostMapping("/admin/update")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<AdminDto> updateAdmin(@RequestHeader("Authorization") String authorization, @RequestBody AdminDto adminDto) {
        return new ResponseEntity<>(adminService.azurirajAdmina(adminDto), HttpStatus.OK);
    }

    @PostMapping("/admin/delete")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<AdminDto> deleteAdmin(@RequestHeader("Authorization") String authorization, @RequestBody String adminId) {
        adminService.ukloniAdmina(adminId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //MANAGER

    @PostMapping("/manager/add")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<ManagerDto> addManager(@RequestHeader("Authorization") String authorization, @RequestBody CreateManagerDto createManagerDto) {
        return new ResponseEntity<>(managerService.dodajManagera(createManagerDto), HttpStatus.OK);
    }
    @PostMapping("/manager/register")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<ManagerDto> sendMailManager(@RequestHeader("Authorization") String authorization, @RequestBody CreateManagerDto createManagerDto) {
        return new ResponseEntity<>(managerService.register(createManagerDto), HttpStatus.OK);
    }

    @PostMapping("/manager/update")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    ResponseEntity<ManagerDto> updateManager(@RequestHeader("Authorization") String authorization, @RequestBody ManagerDto ManagerDto) {
        return new ResponseEntity<>(managerService.azurirajManagera(ManagerDto), HttpStatus.OK);
    }

    @PostMapping("/manager/ban")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    ResponseEntity<ManagerDto> banManager(@RequestHeader("Authorization") String authorization, @RequestBody String ManagerId) {
        return new ResponseEntity<ManagerDto>(managerService.banujManagera(ManagerId), HttpStatus.OK);
    }

    @PostMapping("/manager/unban")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    ResponseEntity<ManagerDto> unbanManager(@RequestHeader("Authorization") String authorization, @RequestBody String ManagerId) {
        return new ResponseEntity<ManagerDto>(managerService.unbanujManagera(ManagerId), HttpStatus.OK);
    }

    @PostMapping("/manager/odobri")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    ResponseEntity<ManagerDto> odobriManager(@RequestHeader("Authorization") String authorization, @RequestBody String ManagerId) {
        return new ResponseEntity<ManagerDto>(managerService.odobriManagera(ManagerId), HttpStatus.OK);
    }


    //USER
    @PostMapping("/user/add")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto> addUser(@RequestHeader("Authorization") String authorization, @RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.dodajUsera(createUserDto), HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.register(createUserDto), HttpStatus.OK);
    }

    @PostMapping("/user/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.azurirajUsera(userDto), HttpStatus.OK);
    }

    @PostMapping("/user/ban")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto> banUser(@RequestHeader("Authorization") String authorization, @RequestBody String Json) {
        return new ResponseEntity<>(userService.banujUsera(Json), HttpStatus.OK);

    }

    @PostMapping("/user/unban")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<UserDto> unbanUser(@RequestHeader("Authorization") String authorization, @RequestBody String Json) {
        return new ResponseEntity<>(userService.unbanujUsera(Json), HttpStatus.OK);

    }
    @PostMapping("/user/odobri")
    public ResponseEntity<UserDto> odobriUser(@RequestBody String Json) {
        return new ResponseEntity<>(userService.odobriUsera(Json), HttpStatus.OK);

    }

    @PostMapping("/user/mail")
    public ResponseEntity<String> promenilozinku(@RequestBody String Json) {
        return new ResponseEntity<>(userService.vratimail(Json), HttpStatus.OK);

    }

    @PostMapping("/user/restartpassword")
    public ResponseEntity<UserDto> vratimail(@RequestBody String Json) {
        userService.resetujlozinku(Json);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("/user/updatedan")
    public ResponseEntity<UserDto> updatedanUser(@ApiIgnore Pageable pageable, @RequestBody String Json) {
        Page<RankDto> rankovi= rankService.nadjiSveRankove(pageable);
        return new ResponseEntity<>(userService.updatedan(Json, rankovi), HttpStatus.OK);

    }

    @GetMapping("/user/discount/{id}")
    public ResponseEntity<Float> discountUser(@ApiIgnore Pageable pageable, @PathVariable("id") String id) {
        Page<RankDto> rankovi= rankService.nadjiSveRankove(pageable);
        return new ResponseEntity<>(userService.discount(id, rankovi), HttpStatus.OK);
    }

    @PostMapping("/user/discount")
    public ResponseEntity<Float> discountUse2r(@ApiIgnore Pageable pageable, @RequestBody String Json) {
        Page<RankDto> rankovi= rankService.nadjiSveRankove(pageable);
        return new ResponseEntity<>(userService.discount(Json, rankovi), HttpStatus.OK);

    }

    //RANK
    @PostMapping("/rank/add")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<RankDto> addRank(@RequestHeader("Authorization") String authorization, @RequestBody CreateRankDto createRankDto) {
        return new ResponseEntity<>(rankService.dodajRank(createRankDto), HttpStatus.OK);
    }

    @PostMapping("/rank/update")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<RankDto> updateRank(@RequestHeader("Authorization") String authorization, @RequestBody RankDto rankDto) {
        return new ResponseEntity<>(rankService.azurirajRank(rankDto), HttpStatus.OK);
    }

    @PostMapping("/rank/delete")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<RankDto> deleteRank(@RequestHeader("Authorization") String authorization, @RequestBody String Json) {
        rankService.deleteById(Json);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody TokenRequestDto tokenRequestDto) {
        ResponseEntity<TokenResponseDto> res3 = new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
        if (res3.getBody() != null) {
            return res3;
        }

        ResponseEntity<TokenResponseDto> res2 = new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
        if (res2.getBody() != null) {
            return res2;
        }

        ResponseEntity<TokenResponseDto> res1 = new ResponseEntity<>(adminService.login(tokenRequestDto), HttpStatus.OK);
        if (res1.getBody() != null) {
            return res1;
        }

        throw new NotFoundException(String.format("User with username: %s and password: %s not found.", tokenRequestDto.getUsername(), tokenRequestDto.getPassword()));
    }
}
