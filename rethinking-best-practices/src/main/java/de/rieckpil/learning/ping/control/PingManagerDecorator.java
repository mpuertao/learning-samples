package de.rieckpil.learning.ping.control;

import de.rieckpil.learning.ping.entity.Ping;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class PingManagerDecorator implements PingService {

    @Inject
    @Delegate
    PingService pingService;

    @Override
    public Ping getPing() {
        System.out.println("DECORATING THE CALL TO PING MANAGER");
        return new Ping(pingService.getPing().getContent().toUpperCase());
    }

    @Override
    public void doAsyncWork() {
        System.out.println("DECORATING ASYNC CALL TO PING MANAGER");
        pingService.doAsyncWork();
    }
}
