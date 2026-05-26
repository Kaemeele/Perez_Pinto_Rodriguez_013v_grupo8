package BullyCars.API_Gateway.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("clientes-service", r -> r.path("/api/v1/clientes/**")
                        .uri("lb://clientes-service"))
                .route("citas-service", r -> r.path("/api/v1/citas/**")
                        .uri("lb://citas-service"))
                .route("vehiculos-service", r -> r.path("/api/v1/vehiculos/**")
                        .uri("lb://vehiculos-service"))
                .route("inventario-service", r -> r.path("/api/v1/inventario/**")
                        .uri("lb://inventario-service"))
                .route("reparaciones-service", r -> r.path("/api/v1/reparaciones/**")
                        .uri("lb://reparaciones-service"))
                .route("estetica-service", r -> r.path("/api/v1/estetica/**")
                        .uri("lb://estetica-service"))
                .route("facturacion-service", r -> r.path("/api/v1/facturacion/**")
                        .uri("lb://facturacion-service"))
                .route("proveedores-service", r -> r.path("/api/v1/proveedores/**")
                        .uri("lb://proveedores-service"))
                .route("notificaciones-service", r -> r.path("/api/v1/notificaciones/**")
                        .uri("lb://notificaciones-service"))
                .route("resenas-service", r -> r.path("/api/v1/resenas/**")
                        .uri("lb://resenas-service"))
                .build();
    }
}
