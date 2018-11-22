package no.nav.kafka;

class ProducerException extends RuntimeException {

    ProducerException(Exception e) {
        super(e);
    }

}
