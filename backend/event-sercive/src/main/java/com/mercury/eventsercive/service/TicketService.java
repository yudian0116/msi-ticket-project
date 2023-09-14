package com.mercury.eventsercive.service;

import com.mercury.eventsercive.bean.Ticket;
import com.mercury.eventsercive.dao.TicketDao;

import dto.InventoryResponse;
import dto.TicketResponse;
import http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    WebClient.Builder webClientBuilder;

    public void save(Ticket ticket) {ticketDao.save(ticket);}

    public Ticket getTicketById(int id) {
        Optional<Ticket> t = ticketDao.findById(id);
        if (t.isPresent()) {
            return t.get();
        } else {
            return null;
        }
    }

    public List<TicketResponse> getTicketsFromList(List<Integer> ticketId) {
        return ticketDao.findTicketByIdIn(ticketId).stream()
                .map(ticket ->
                    TicketResponse.builder()
                            .tid(ticket.getId())
                            .price(ticket.getPrice())
                            .build()
                ).toList();
    }

    public List<Ticket> getTicketsByEvent(int event_id) {
        return ticketDao.findByEventId(event_id);
    }

    public void deleteById(int id) {
        ticketDao.deleteById(id);
        // TODO: send request to delete inventory of this ticket
        InventoryResponse inventoryResponse = new InventoryResponse(id, 0);
        webClientBuilder.build().put()
                .uri("http://inventory-service/inventories")
                .body(Mono.just(inventoryResponse), InventoryResponse.class)
        .retrieve().bodyToMono(Response.class).block();
    }

    public Response update(Ticket ticket) {
        Ticket t = ticketDao.findById(ticket.getId()).get();
        t.setPrice(ticket.getPrice());
        t.setEvent(ticket.getEvent());
        t.setType(ticket.getType());
        try {
            ticketDao.save(t);
        } catch (Exception e) {
            return new Response(false);
        }
        return new Response(true);
    }

}
