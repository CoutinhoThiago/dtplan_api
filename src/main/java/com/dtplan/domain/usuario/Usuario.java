package com.dtplan.domain.usuario;

import com.dtplan.domain.treino.Treino;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Permissao permissao;

    private String nome;
    private String cpf;

    @Column(name = "data_nascimento")
    private Date dataNascimento; // meses

    private int altura; // cm

    @Column(name = "peso_atual")
    private int pesoAtual; // kg

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_atividade")
    private NivelAtividade nivelAtividade;

    @Enumerated(EnumType.STRING)
    private Objetivo objetivo;

    @OneToMany(mappedBy = "usuario")
    private List<Treino> treinos;

    public Usuario(String email, String senha, Permissao permissao, String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;


        this.email = email;
        this.senha = senha;

        this.permissao = permissao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.permissao == Permissao.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    public void atualizarDados(Optional<String> nome, Optional<String> cpf, Optional<Date> dataNascimento, Optional<Integer> altura, Optional<Integer> pesoAtual, Optional<String> nivelAtividade, Optional<String> objetivo) {
        nome.ifPresent(n -> this.nome = n);
        cpf.ifPresent(c -> this.cpf = c);
        dataNascimento.ifPresent(d -> this.dataNascimento = d);
        altura.ifPresent(a -> this.altura = a);
        pesoAtual.ifPresent(p -> this.pesoAtual = p);
        nivelAtividade.ifPresent(n -> this.nivelAtividade = NivelAtividade.valueOf(n));
        objetivo.ifPresent(o -> this.objetivo = Objetivo.valueOf(o));
    }

    @Override
    public String getPassword() {
        return senha;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}