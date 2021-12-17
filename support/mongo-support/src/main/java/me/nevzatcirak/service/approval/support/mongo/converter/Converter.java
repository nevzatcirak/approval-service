package me.nevzatcirak.service.approval.support.mongo.converter;

import java.util.*;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 06/12/2021
 */
public interface Converter<Model, Document> {
    Model toModel(Document document);

    Document toDocument(Model model);

    default List<Model> toModelList(Collection<Document> documents) {
        if (Objects.isNull(documents)) {
            return null;
        }
        List<Model> modelList = new ArrayList<>();
        documents.forEach(document -> {
            modelList.add(toModel(document));
        });
        return modelList;
    }

    default List<Document> toDocumentList(Collection<Model> models) {
        if (Objects.isNull(models)) {
            return null;
        }
        List<Document> documentList = new ArrayList<>();
        models.forEach(model -> {
            documentList.add(toDocument(model));
        });
        return documentList;
    }

    default Set<Model> toModelSet(Set<Document> documents) {
        if (Objects.isNull(documents)) {
            return null;
        }
        Set<Model> modelSet = new HashSet<>();
        documents.forEach(document -> {
            modelSet.add(toModel(document));
        });
        return modelSet;
    }

    default Set<Document> toDocumentSet(Set<Model> models) {
        if (Objects.isNull(models)) {
            return null;
        }
        Set<Document> documentSet = new HashSet<>();
        models.forEach(model -> {
            documentSet.add(toDocument(model));
        });
        return documentSet;
    }
}
