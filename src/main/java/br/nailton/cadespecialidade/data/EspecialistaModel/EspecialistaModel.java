package br.nailton.cadespecialidade.data.EspecialistaModel;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "especialista")
public class EspecialistaModel {

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "especialidade")
  private String especialidade;

  public EspecialistaModel() {
    this.id = UUID.randomUUID();
  }

  public EspecialistaModel(String name, String especialidade) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.especialidade = especialidade;
  }

  public UUID getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEspecialidade() {
    return this.especialidade;
  }

  public void setEspecialidades(String especialidade) {
    this.especialidade = especialidade;
  }
}
