import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "demo"
    request {
        url "/user/1"
        method GET()
    }

    response {
        status 200
        body("{\n" +
                "  \"id\": 1,\n" +
                "  \"firstName\": \"Ryan\",\n" +
                "  \"lastName\": \"Benjamin\",\n" +
                "  \"emailID\": \"ryanb@gmail.com\"\n" +
                "}")
    }
}