package com.okaka.onenightjinroh.application.service.talk;

import com.okaka.onenightjinroh.application.domain.Role;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Co> getCos() {
        return cos;
    }

    public CoState update(Long playerId, String roleName) {
        List<Co> newCos = this.cos.stream()
                .filter(co -> !co.getId().equals(playerId))
                .collect(Collectors.toList());
        newCos.add(new Co(playerId, Role.ofByEngName(roleName)));

        return CoState.build(newCos);
    }

    public boolean equalState(CoState coState) {
        if (coState.getCos().size() != this.getCos().size()) {
            return false;
        }

        for (Co co : this.cos) {
            long count = coState.getCos().stream().filter(c ->
                    c.getId().equals(co.getId()) && c.getRole().equals(co.getRole())
            ).count();

            if (count == 0) {
                return false;
            }
        }

        return true;
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
}
