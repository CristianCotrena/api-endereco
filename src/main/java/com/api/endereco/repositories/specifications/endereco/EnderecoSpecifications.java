package com.api.endereco.repositories.specifications.endereco;

import com.api.endereco.entity.models.EnderecoModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class EnderecoSpecifications {

    public static Specification<EnderecoModel> buscarPorUmId (String idCliente, String idFornecedor, String idFuncionario) {
        return (root, query, criteriaBuilder) -> {

            Predicate predicate = criteriaBuilder.disjunction();

            if (idCliente != null && !idCliente.isEmpty()) {
                predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(root.get("idCliente"), UUID.fromString(idCliente)));
            }
            if (idFornecedor != null && !idFornecedor.isEmpty()) {
                predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(root.get("idFornecedor"), UUID.fromString(idFornecedor)));
            }
            if (idFuncionario != null && !idFuncionario.isEmpty()) {
                predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(root.get("idFuncionario"), UUID.fromString(idFuncionario)));
            }
            return predicate;
        };
    }
}
