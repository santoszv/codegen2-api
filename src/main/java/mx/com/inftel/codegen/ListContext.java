package mx.com.inftel.codegen;

import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListContext<X> extends CountContext<X> {

    private final List<Order> orders;
    private LockModeType lockMode;
    private int firstResult;
    private int maxResults;

    public ListContext(Root<X> root, CriteriaBuilder criteriaBuilder) {
        super(root, criteriaBuilder);
        this.orders = new ArrayList<>();
        this.lockMode = LockModeType.NONE;
        this.firstResult = -1;
        this.maxResults = -1;
    }

    public List<Order> getOrders() {
        return Objects.requireNonNull(orders);
    }

    public LockModeType getLockMode() {
        return Objects.requireNonNull(lockMode);
    }

    public void setLockMode(LockModeType lockMode) {
        this.lockMode = Objects.requireNonNull(lockMode);
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }
}
