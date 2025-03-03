package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.mongo.VorstellungStats;
import com.kino.repository.VorstellungStatsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatsReceiver {

    @Autowired
    private VorstellungStatsRepository statsRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "bookingStatsQueue")
    public void handleBookingStatsEvent(String message) {
        try {
            Map<String, Object> event = objectMapper.readValue(message, Map.class);
            Long vorstellungId = ((Number) event.get("vorstellungId")).longValue();
            String filmTitel = (String) event.get("filmTitel");
            int summe = ((Number) event.get("summe")).intValue();

            VorstellungStats stats = statsRepo.findById(vorstellungId)
                    .orElse(new VorstellungStats(vorstellungId, filmTitel, 0));
            stats.setTotalRevenue(stats.getTotalRevenue() + summe);
            statsRepo.save(stats);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}