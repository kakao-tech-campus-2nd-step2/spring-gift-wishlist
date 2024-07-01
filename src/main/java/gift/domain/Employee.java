package gift.domain;

import java.util.concurrent.atomic.AtomicLong;


public record Employee(Long id, String name, String email, String address, String phone) {

    private static final AtomicLong sequence = new AtomicLong(3);

    public Employee(String name, String email, String address, String phone) {
        this(sequence.incrementAndGet(), name, email, address, phone);
    }

    public void setInitialSequence(Long id) {
        sequence.set(id);
    }
}

