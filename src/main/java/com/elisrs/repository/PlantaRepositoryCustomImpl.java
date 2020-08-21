package com.elisrs.repository;

import com.elisrs.dto.AtributoPlantaDTO;
import com.elisrs.dto.PlantaDTO;
import com.elisrs.enty.Planta;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public class PlantaRepositoryCustomImpl implements PlantaRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;//gerencia o banco de dados via jpa (spring faz isso)

    public List<Planta> findPlant(AtributoPlantaDTO filter){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select p from Planta p where status is true ");

        //Categoria
        if(filter.getCategorias()!=null && !filter.getCategorias().isEmpty()){
            stringBuilder.append("and exists (select c from Categoriavegetal c where c.id in (:idsCategorias) and c member of p.categoriavegetals ) ");
        }

        //Forma
        if(filter.getFormas()!=null && !filter.getFormas().isEmpty()){
            stringBuilder.append("and exists (select f from Formadaplanta f where f.id in (:idsFormas) and f member of p.formadaplantas ) ");
        }
        //Clima
        if(filter.getClimas()!=null && !filter.getClimas().isEmpty()){
            stringBuilder.append("and exists (select cl from Clima cl where cl.id in (:idsClimas) and cl member of p.climas ) ");
        }
        //CorDaFlor
        if(filter.getCordasflores()!=null && !filter.getCordasflores().isEmpty()){
            stringBuilder.append("and exists (select cfl from Cordaflor cfl where cfl.id in (:idsCordasflores) and cfl member of p.cordaflors ) ");
        }

        //CorDaFolha
        if(filter.getCordasfolhas()!=null && !filter.getCordasfolhas().isEmpty()){
            stringBuilder.append("and exists (select cf from Cordafolha cf where cf.id in (:idsCordasfolhas) and cf member of p.cordafolhas ) ");
        }

        //Exposicao
        if(filter.getExposicoes()!=null && !filter.getExposicoes().isEmpty()){
            stringBuilder.append("and exists (select ex from Exposicaosolar ex where ex.id in (:idsExposicoes) and ex member of p.exposicaosolars ) ");
        }
        //Persistencia
        if(filter.getPersistencia()!=null && !filter.getPersistencia().isEmpty()){
            stringBuilder.append("and exists (select pr from Persistencia pr where pr.id in (:idsPersistencia) and pr member of p.persistencia ) ");
        }

        //TexturaFolha
        if(filter.getTexturasdafolha()!=null && !filter.getTexturasdafolha().isEmpty()){
            stringBuilder.append("and exists (select txf from Texturadafolha txf where txf.id in (:idsTesturadasfolhas) and txf member of p.texturafolhas ) ");
        }

        //TexturaTronco
        if(filter.getTexturasdotronco()!=null && !filter.getTexturasdotronco().isEmpty()){
            stringBuilder.append("and exists (select txt from Texturadotronco txt where txt.id in (:idsTesturadostronco) and txt member of p.texturatroncos ) ");
        }

        TypedQuery<Planta> query = entityManager.createQuery(stringBuilder.toString(), Planta.class);
        if(filter.getCategorias()!=null && !filter.getCategorias().isEmpty()){
            query.setParameter("idsCategorias", filter.getCategorias());
        }
        if(filter.getFormas()!=null && !filter.getFormas().isEmpty()){
            query.setParameter("idsFormas", filter.getFormas());
        }
        if(filter.getClimas()!=null && !filter.getClimas().isEmpty()){
            query.setParameter("idsClimas", filter.getClimas());
        }
        if(filter.getCordasflores()!=null && !filter.getCordasflores().isEmpty()){
            query.setParameter("idsCordasflores", filter.getCordasflores());
        }
        if(filter.getCordasfolhas()!=null && !filter.getCordasfolhas().isEmpty()){
            query.setParameter("idsCordasfolhas", filter.getCordasfolhas());
        }
        if(filter.getExposicoes()!=null && !filter.getExposicoes().isEmpty()){
            query.setParameter("idsExposicoes", filter.getExposicoes());
        }
        if(filter.getPersistencia()!=null && !filter.getPersistencia().isEmpty()){
            query.setParameter("idsPersistencia", filter.getPersistencia());
        }
        if(filter.getTexturasdafolha()!=null && !filter.getTexturasdafolha().isEmpty()){
            query.setParameter("idsTesturadasfolhas", filter.getTexturasdafolha());
        }
        if(filter.getTexturasdotronco()!=null && !filter.getTexturasdotronco().isEmpty()){
            query.setParameter("idsTesturadostronco", filter.getTexturasdotronco());
        }



        return query.getResultList();
    }
}
