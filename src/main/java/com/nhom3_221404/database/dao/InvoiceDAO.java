package com.nhom3_221404.database.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nhom3_221404.entity.Invoice;
import com.nhom3_221404.util.Pageable;

public interface InvoiceDAO {
    List<Invoice> selectAll(@Param("page") Pageable pageable);

    int countAll();

    List<Invoice> selectLikes(@Param("p") String pattern, @Param("page") Pageable pageable);

    int countLikes(@Param("p") String pattern);

    Invoice selectById(String id);

    void insert(Invoice invoice);

    void update(Invoice invoice);
    
    void delete(Invoice invoice);

    void deleteAll();

    void deleteTypeRecord(Invoice invoice);

    Boolean checkExistsByType(Invoice invoice);
}
