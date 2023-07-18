package br.nailton.cadespecialidade.data.EspecialistaService;

import br.nailton.cadespecialidade.data.EspecialistaModel.EspecialistaModel;
import br.nailton.cadespecialidade.data.Repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EspecialidadeService {

  @Autowired
  private EspecialidadeRepository especialidadeRepository;

  public void insertEspecialista(EspecialistaModel especialista) {
    this.especialidadeRepository.save(especialista);
  }

  public List<EspecialistaModel> listEspecialistas() {
    return this.especialidadeRepository.findAll();
  }

  public EspecialistaModel listEspecialista(UUID id) {
    return this.especialidadeRepository.findById(id).orElseThrow(null);
  }

  public void updateEspecialista(EspecialistaModel especialista) {
    this.especialidadeRepository.save(especialista);
  }

  public void delEspecialista(UUID id) {
    this.especialidadeRepository.deleteById(id);
  }

  public void delAll() {
    this.especialidadeRepository.deleteAll();
  }
}
