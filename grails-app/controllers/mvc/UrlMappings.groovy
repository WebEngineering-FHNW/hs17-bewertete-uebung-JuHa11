package mvc

class UrlMappings {

    static mappings = {
        // "/rooms"(resources: "room")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(controller: "Socrative", action: "index")
        "/socrative/addQuestionBlock"(controller: "Socrative", action: "addQuestionBlock")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
