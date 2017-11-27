package com.nbenja.springcloud.cloudstreamkafkaavroexample.utils;

import com.nbenja.springcloud.cloudstreamkafkaavroexample.avro.domain.Request;

import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RequestSerialization {

    public byte[] requestSerialize(Request object) throws IOException {
        DatumWriter writer = new GenericDatumWriter(Request.getClassSchema());
        try(ByteArrayOutputStream output = new ByteArrayOutputStream()){
            BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(output, null);
            writer.write(object, encoder);
            encoder.flush();
            return output.toByteArray();
        }
    }

    public Request requestDeserialize(byte[] payload) throws IOException {
        DatumReader<Request> dr = new SpecificDatumReader<Request>(Request.getClassSchema());
        Decoder decoder = DecoderFactory.get().binaryDecoder(payload, null);
        Request request = null;
        try {
            request = dr.read(null , decoder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }
}