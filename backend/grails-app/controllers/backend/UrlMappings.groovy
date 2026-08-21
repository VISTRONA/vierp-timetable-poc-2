package backend

class UrlMappings {

    static mappings = {
        "/api/health"(controller: "health", action:"index",method:"get")
        "/$namespace/$controller/$action?/$id?(.$format)?" {}
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
