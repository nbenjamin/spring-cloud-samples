import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "User service message contract"

    label ("user-message")

    input {
        triggeredBy("publishMessage()")
    }

    outputMessage {
        sentTo("userTopic")
        body([ id: 1,
               firstName: "Adam",
               lastName: "Benjamin",
               emailID: "adamBen@gmail.com"
                ])
    }
}