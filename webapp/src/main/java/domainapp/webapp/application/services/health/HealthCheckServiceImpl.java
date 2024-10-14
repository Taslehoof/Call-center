package domainapp.webapp.application.services.health;

import org.springframework.stereotype.Service;

import org.apache.causeway.applib.services.health.Health;
import org.apache.causeway.applib.services.health.HealthCheckService;

import domainapp.modules.simple.dom.so.SimpleObjects;
import domainapp.modules.simple.dom.so.usuario.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Service
@Named("domainapp.HealthCheckServiceImpl")
public class HealthCheckServiceImpl implements HealthCheckService {

    private final SimpleObjects simpleObjects;
    private final Usuarios userRepo;

    @Inject
    public HealthCheckServiceImpl(SimpleObjects simpleObjects, Usuarios userRepo) {
        this.simpleObjects = simpleObjects;
        this.userRepo = userRepo;
    }

    @Override
    public Health check() {
        try {
            simpleObjects.ping();
            userRepo.ping();
            return Health.ok();
        } catch (Exception ex) {
            return Health.error(ex);
        }
    }
}
