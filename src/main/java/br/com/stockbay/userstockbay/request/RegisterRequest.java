package br.com.stockbay.userstockbay.request;

import br.com.stockbay.userstockbay.domain.user.AuthRole;

public record RegisterRequest(String login, String password, AuthRole role) {
}
