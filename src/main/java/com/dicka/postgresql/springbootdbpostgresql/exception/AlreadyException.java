package com.dicka.postgresql.springbootdbpostgresql.exception;

public class AlreadyException extends RuntimeException{

    public AlreadyException(final String pesan_kesalahan){
        super(pesan_kesalahan);
    }
}
