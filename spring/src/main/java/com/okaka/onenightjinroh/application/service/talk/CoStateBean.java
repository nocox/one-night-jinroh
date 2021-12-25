package com.okaka.onenightjinroh.application.service.talk;

import java.util.List;
import java.util.stream.Collectors;

public class CoStateBean {
    private List<CoBean> coBeans;

    public List<CoBean> toList() {
        return this.coBeans;
    }

    public CoStateBean(List<CoBean> coBeans) {
        this.coBeans = coBeans;
    }

    public static CoStateBean fromDomain(CoState coState) {
        final var coBeans = coState.getCos().stream().map(co -> {
            return new CoBean(co.getId(), co.getRole().getRoleEngName());
        }).collect(Collectors.toList());
        return new CoStateBean(coBeans);
    }

    public static class CoBean {
        private Long id;
        private String role;

        public CoBean(Long id, String role) {
            this.id = id;
            this.role = role;
        }

        public Long getId() {
            return id;
        }

        public String getRole() {
            return role;
        }
    }
}
