package no.nav.kafka;

public class ProducerException extends RuntimeException {

    public ProducerException(Exception e) {
        super(e);
    }

}
