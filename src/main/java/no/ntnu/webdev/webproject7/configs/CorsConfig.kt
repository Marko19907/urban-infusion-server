package no.ntnu.webdev.webproject7.configs

import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CorsConfig : OncePerRequestFilter() {

    private val allowedOrigins = hashSetOf(
        "http://localhost:3000",
        "https://localhost:3000",
        "https://urbaninfusion.netlify.app"
    );

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var accessControlAllowOrigin = "";
        if (this.allowedOrigins.contains(request.getHeader("Origin"))) {
            accessControlAllowOrigin = request.getHeader("Origin");
        }

        response.addHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD, OPTIONS")
        response.addHeader(
            "Access-Control-Allow-Headers",
            "Origin, Accept, X-Requested-With, Authorization, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"
        )
        response.addHeader(
            "Access-Control-Expose-Headers",
            "Access-Control-Allow-Origin, Authorization, Access-Control-Allow-Credentials"
        )
        response.addHeader("Access-Control-Allow-Credentials", "true")
        response.addIntHeader("Access-Control-Max-Age", 10)
        if ("OPTIONS" == request.method) {
            response.status = HttpServletResponse.SC_OK;
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
