import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "User service message contract"

    label "user message"

    input {
        triggeredBy('publishMessage()')
    }

    outputMessage {
        sentTo("userTopic")
        body("{\n" +
                "  \"id\": 1,\n" +
                "  \"firstName\": \"Adam\",\n" +
                "  \"lastName\": \"Benjamin\",\n" +
                "  \"emailID\": \"adamBen@gmail.com\"\n" +
                "}")
    }
}