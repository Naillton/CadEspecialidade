package br.nailton.cadespecialidade.data.EspecialistaController;

import br.nailton.cadespecialidade.data.EspecialistaModel.EspecialistaModel;
import br.nailton.cadespecialidade.data.EspecialistaService.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EspecialistaController {

  @Autowired
  private EspecialidadeService especialidadeService;

  @PostMapping(value = "/insertEspecialista",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EspecialistaModel> insertEspecialista(@RequestBody EspecialistaModel especialista) {
    ResponseEntity isValid = vaildateCamps(especialista.getName(), especialista.getEspecialidade());
    try {
      if (isValid == null) {
        especialidadeService.insertEspecialista(especialista);
        return ResponseEntity.status(HttpStatus.OK).body(especialista);
      }
      return isValid;
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping(value = "/listEspecialista")
  public ResponseEntity<List<EspecialistaModel>> listEspecialista() {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(especialidadeService.listEspecialistas());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping(value = "/listEspecialista/{id}")
  public ResponseEntity<EspecialistaModel> listEspecialista(@PathVariable UUID id) {
    EspecialistaModel isNull = especialidadeService.listEspecialista(id);
    try {
      if (isNull == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      return ResponseEntity.status(HttpStatus.OK).body(especialidadeService.listEspecialista(id));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping(value = "/updateEspecialista/{id}")
  public ResponseEntity<EspecialistaModel> updateEspecialista(
          @PathVariable UUID id,
          @RequestBody EspecialistaModel especialista) {
    EspecialistaModel especialistaNull = especialidadeService.listEspecialista(id);
    ResponseEntity isValid = vaildateCamps(especialista.getName(), especialista.getEspecialidade());
    try {
      if (especialistaNull == null) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
      } else if (isValid != null) {
        return isValid;
      }
      especialistaNull.setName(especialista.getName());
      especialistaNull.setEspecialidades(especialista.getEspecialidade());
      especialidadeService.updateEspecialista(especialistaNull);
      return ResponseEntity.status(HttpStatus.OK).body(especialista);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping(value = "/delEspecialista/{id}")
  public ResponseEntity delEspecialista(@PathVariable UUID id) {
    EspecialistaModel isNull = especialidadeService.listEspecialista(id);
    try {
      if (isNull == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }
      especialidadeService.delEspecialista(id);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping(value = "/delEspecialistas")
  public ResponseEntity delEspecialistas() {
    try {
      especialidadeService.delAll();
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  private ResponseEntity vaildateCamps(String name, String especialidade) {
    if (name.length() < 8 || especialidade.length() < 2) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("campos/invalidos");
    }
    return null;
  }

}
