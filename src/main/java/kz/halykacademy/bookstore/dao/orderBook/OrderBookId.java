package kz.halykacademy.bookstore.dao.orderBook;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderBookId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private Long bookId;

    public OrderBookId(){}

    public OrderBookId(Long orderId, Long bookId) {
        super();
        this.orderId = orderId;
        this.bookId = bookId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
