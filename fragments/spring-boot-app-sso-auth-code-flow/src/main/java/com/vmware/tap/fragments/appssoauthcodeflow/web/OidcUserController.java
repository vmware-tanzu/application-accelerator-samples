package com.vmware.tap.fragments.appssoauthcodeflow.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OidcUserController {

    @GetMapping("/protected/profile")
    public String user(Model model, @AuthenticationPrincipal OidcUser authenticatedUser) {
        model.addAttribute("username", authenticatedUser.getClaims().get("sub"));
        return "protected/profile.html";
    }
}
