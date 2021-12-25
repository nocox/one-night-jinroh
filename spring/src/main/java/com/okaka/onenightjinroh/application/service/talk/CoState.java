package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.domain.Role;

import java.util.List;

public class CoState {
    private List<Co> cos;

    public static CoState build(List<Co> cos) {
        return new CoState(cos);
    }

    public static CoState buildEmpty() {
        return new CoState(List.of());
    }

    public CoState(List<Co> cos) {
        this.cos = cos;
    }

    public static class Co {
        private Long id;
        private Role role;

        public Co(Long id, Role role) {
            this.id = id;
            this.role = role;
        }

        public Long getId() {
            return id;
        }

        public Role getRole() {
            return role;
        }
    }

    public List<Co> getCos() {
        return cos;
    }
}
