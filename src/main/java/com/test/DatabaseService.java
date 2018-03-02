package com.test;

import model.Goods;
import model.Invoice;

public interface DatabaseService {
    boolean addInvoice(Invoice invoice);
    boolean addGoods(Goods goods);
}
