package br.nailton.cadespecialidade.data.Repository;

import br.nailton.cadespecialidade.data.EspecialistaModel.EspecialistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EspecialidadeRepository extends JpaRepository<EspecialistaModel, UUID> {
}
