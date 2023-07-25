package training.balatonstormsignal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
@Slf4j
public class BalatonstormsignalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalatonstormsignalApplication.class, args);
	}

	@Bean
	public Function<Flux<String>, Flux<String>> download() {
		return stringFlux ->
				stringFlux
						.doOnNext(s -> log.info("Message has come: {}", s))
								.flatMap(s ->
										WebClient.builder().baseUrl("https://katasztrofavedelem.hu/application/uploads/cache/viharjelzo/SWSStations.json")
												.build()
												.get()
												.retrieve().
												bodyToMono(String.class)
								)
				;
	}

//	@Bean
//	public Consumer<Flux<StationDocument>> log() {
//		return stationFlux -> stationFlux.flatMapIterable(StationDocument::getStations).subscribe(station -> log.info("Station: {}", station));
//	}

	@Bean
	public Function<Flux<StationDocument>, Flux<Station>> parse() {
		return stationFlux -> stationFlux.flatMapIterable(StationDocument::getStations);
	}

	@Bean
	public Function<Flux<Station>, Flux<StormSignalEvent>> convert() {
		return stationFlux ->
				stationFlux.map(station -> new StormSignalEvent(station.getHwId(), station.getGroupId(), station.getName(), station.getLevel()));
	}

	@Bean
	public Function<Flux<StormSignalEvent>, Flux<StormSignalEvent>> filter() {
		return stationFlux ->
				stationFlux
						.filter(event -> event.getGroupId().startsWith("B"))
				;
	}

//	@Bean
//	public Consumer<Flux<StormSignalEvent>> logEvent() {
//		return stormSignalEventFlux -> stormSignalEventFlux.subscribe(e -> log.info("Event: {}", e));
//	}
}
