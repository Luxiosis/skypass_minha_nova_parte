package com.startech.skypass;

import com.startech.skypass.dao.AdressDAO;
import com.startech.skypass.dao.ClientDAO;
import com.startech.skypass.dao.TicketDAO;
import com.startech.skypass.dao.AirlineDAO;
import com.startech.skypass.dao.AircraftDAO;
import com.startech.skypass.dao.FlightDAO;
import com.startech.skypass.dao.AirportDAO;
import com.startech.skypass.repository.AdressRepository;
import com.startech.skypass.repository.AirlineRepository;
import com.startech.skypass.repository.ClientRepository;
import com.startech.skypass.repository.TicketRepository;
import com.startech.skypass.repository.AirportRepository;
import com.startech.skypass.repository.FlightRepository;
import com.startech.skypass.repository.AircraftRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class SkypassController {

    //--------------------------------------|CLIENT|------------------------------------------------------
    @Autowired
    private ClientRepository clientRepository;
    public List<ClientDTO> ClientsList = new ArrayList<ClientDTO>();
    public HashMap<Integer, ClientDTO> clients = new HashMap<Integer, ClientDTO>();

    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> addClient(@RequestBody @Valid ClientDTO c) {
        ClientDAO clientePersisted = clientRepository.save(c.toDAO());
        c.ativar();//------------------------ ATIVAR
        String tamanhoLista = String.valueOf(clientRepository.findAll().size());
        System.out.println("Cliente Cadastrado com SUCESSO!" + " - Quantidade de Clientes: " + tamanhoLista);
        System.out.println(c.toString());
        return new ResponseEntity<ClientDTO>(clientePersisted.toDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}/update")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable("id") Long id, @RequestBody ClientDTO c) {
        c.setId(id);
        ClientDAO clientUpdated = clientRepository.save(c.toDAO());
        return new ResponseEntity<ClientDTO>(clientUpdated.toDTO(), HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok().body(clientRepository.findAll()
                .stream()
                .map(clientDAO -> clientDAO.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        Optional<ClientDAO> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return new ResponseEntity<ClientDTO>(client.get().toDTO(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/clients/{id}/delete")
    public ResponseEntity<ClientDTO> deleteClientById(@PathVariable("id") Long id) {
        ClientDAO client = new ClientDAO();
        client.inativar(); //--------------------- INATIVAR
        client.setId(id);
        clientRepository.delete(client);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/clients/{id}/ativar")
    public void ativarClient(Long id) {
        clientRepository.findById(id).ifPresent(client -> {
            client.ativar();
            clientRepository.save(client);
        });
    }

    @PostMapping("/clients/{id}/inativar")
    public void inativarClient(Long id) {
        clientRepository.findById(id).ifPresent(client -> {
            client.inativar();
            clientRepository.save(client);
        });
    }

    //--------------------------------------- |ADRESS| -----------------------------------------------------
    @Autowired
    private AdressRepository adressRepository;
    public List<AdressDTO> AdressList = new ArrayList<AdressDTO>();
    public HashMap<Integer, AdressDTO> adresses = new HashMap<Integer, AdressDTO>();

    @PostMapping("/adresses")
    public ResponseEntity<AdressDTO> addAdress(@RequestBody @Valid AdressDTO ad) {
        AdressDAO adressPersisted = adressRepository.save(ad.toDAO());
        String tamanhoLista = String.valueOf(adressRepository.findAll().size());
        System.out.println("Endereço Cadastrado com SUCESSO!" + " - Quantidade de Endereços: " + tamanhoLista);
        System.out.println(ad.toString());
        return new ResponseEntity<AdressDTO>(adressPersisted.toDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/adresses/{id}/update")
    public ResponseEntity<AdressDTO> updateAdress(@PathVariable("id") Long id, @RequestBody AdressDTO ad) {
        ad.setId(id);
        AdressDAO adressUpdated = adressRepository.save(ad.toDAO());
        return new ResponseEntity<AdressDTO>(adressUpdated.toDTO(), HttpStatus.OK);
    }

    @GetMapping("/adresses")
    public ResponseEntity<List<AdressDTO>> getAllAdresses() {
        return ResponseEntity.ok().body(adressRepository.findAll()
                .stream()
                .map(adressDAO -> adressDAO.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/adresses/{id}")
    public ResponseEntity<AdressDTO> getAdressById(@PathVariable("id") Long id) {
        Optional<AdressDAO> adress = adressRepository.findById(id);
        if (adress.isPresent()) {
            return new ResponseEntity<AdressDTO>(adress.get().toDTO(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/adresses/{id}/delete")
    public ResponseEntity<AdressDTO> deleteAdressById(@PathVariable("id") Long id) {
        AdressDAO adress = new AdressDAO();
        adress.setId(id);
        adressRepository.delete(adress);
        return ResponseEntity.noContent().build();
    }

    //------------------------------------------------|AIRCRAFT|-------------------------------------------
    @Autowired
    private AircraftRepository aircraftRepository;
    public List<AircraftDTO> AircraftsList = new ArrayList<AircraftDTO>();
    public HashMap<Integer, AircraftDTO> aircrafts = new HashMap<Integer, AircraftDTO>();

    @PostMapping("/aircrafts")
    public ResponseEntity<AircraftDTO> addAircraft(@RequestBody @Valid AircraftDTO af) {
        AircraftDAO aircraftPersisted = aircraftRepository.save(af.toDAO());
        af.ativar();//------------------------ ATIVAR
        String tamanhoLista = String.valueOf(aircraftRepository.findAll().size());
        System.out.println("Aeronave Cadastrada com SUCESSO!" + " - Quantidade de Aeronaves: " + tamanhoLista);
        System.out.println(af.toString());
        return new ResponseEntity<AircraftDTO>(aircraftPersisted.toDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/aircrafts/{id}/update")
    public ResponseEntity<AircraftDTO> updateAircraft(@PathVariable("id") Long id, @RequestBody AircraftDTO af) {
        af.setId(id);
        AircraftDAO aircraftUpdated = aircraftRepository.save(af.toDAO());
        return new ResponseEntity<AircraftDTO>(aircraftUpdated.toDTO(), HttpStatus.OK);
    }

    @GetMapping("/aircrafts")
    public ResponseEntity<List<AircraftDTO>> getAllAircrafts() {
        return ResponseEntity.ok().body(aircraftRepository.findAll()
                .stream()
                .map(aircraftDAO -> aircraftDAO.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/aircrafts/{id}")
    public ResponseEntity<AircraftDTO> getAircraftById(@PathVariable("id") Long id) {
        Optional<AircraftDAO> aircraft = aircraftRepository.findById(id);
        if (aircraft.isPresent()) {
            return new ResponseEntity<AircraftDTO>(aircraft.get().toDTO(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/aircrafts/{id}/delete")
    public ResponseEntity<AircraftDTO> deleteAircraftById(@PathVariable("id") Long id) {
        AircraftDAO aircraft = new AircraftDAO();
        aircraft.inativar(); //--------------------- INATIVAR
        aircraft.setId(id);
        aircraftRepository.delete(aircraft);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/aircrafts/{id}/ativar")
    public void ativarAircraft(Long id) {
        aircraftRepository.findById(id).ifPresent(aircraft -> {
            aircraft.ativar();
            aircraftRepository.save(aircraft);
        });
    }

    @PostMapping("/aircrafts/{id}/inativar")
    public void inativarAircraft(Long id) {
        aircraftRepository.findById(id).ifPresent(aircraft -> {
            aircraft.inativar();
            aircraftRepository.save(aircraft);
        });
    }

    //--------------------------------------|FLIGHT|------------------------------------------------------

    @Autowired
    private FlightRepository flightRepository;
    public List<FlightDTO> FlightsList = new ArrayList<FlightDTO>();
    public HashMap<Integer, FlightDTO> flights = new HashMap<Integer, FlightDTO>();

    @PostMapping("/flights")
    public ResponseEntity<FlightDTO> addFlight(@RequestBody @Valid FlightDTO fl) {
        FlightDAO flightPersisted = flightRepository.save(fl.toDAO());
        fl.ativar();//------------------------ ATIVAR
        String tamanhoLista = String.valueOf(flightRepository.findAll().size());
        System.out.println("Voo Cadastrada com SUCESSO!" + " - Quantidade de voos: " + tamanhoLista);
        System.out.println(fl.toString());
        return new ResponseEntity<FlightDTO>(flightPersisted.toDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/flights/{id}/update")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable("id") Long id, @RequestBody FlightDTO fl) {
        fl.setId(id);
        FlightDAO flightUpdated = flightRepository.save(fl.toDAO());
        return new ResponseEntity<FlightDTO>(flightUpdated.toDTO(), HttpStatus.OK);
    }

    @GetMapping("/flights")
    public ResponseEntity<List<FlightDTO>> getAllFligths() {
        return ResponseEntity.ok().body(flightRepository.findAll()
                .stream()
                .map(flightDAO -> flightDAO.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable("id") Long id) {
        Optional<FlightDAO> flight = flightRepository.findById(id);
        if (flight.isPresent()) {
            return new ResponseEntity<FlightDTO>(flight.get().toDTO(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/flights/{id}/delete")
    public ResponseEntity<FlightDTO> deleteFlightById(@PathVariable("id") Long id) {
        FlightDAO flight = new FlightDAO();
        flight.inativar(); //--------------------- INATIVAR
        flight.setId(id);
        flightRepository.delete(flight);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/flights/{id}/ativar")
    public void ativarFlight(Long id) {
        flightRepository.findById(id).ifPresent(flight -> {
            flight.ativar();
            flightRepository.save(flight);
        });
    }

    @PostMapping("/flights/{id}/inativar")
    public void inativarFlight(Long id) {
        airlineRepository.findById(id).ifPresent(flight -> {
            flight.inativar();
            airlineRepository.save(flight);
        });
    }

    //--------------------------------------|AIRLINE|------------------------------------------------------
    @Autowired
    private AirlineRepository airlineRepository;
    public List<AirlineDTO> AirlinesList = new ArrayList<AirlineDTO>();
    public HashMap<Integer, AirlineDTO> airlines = new HashMap<Integer, AirlineDTO>();

    @PostMapping("/airlines")
    public ResponseEntity<AirlineDTO> addAirline(@RequestBody @Valid AirlineDTO al) {
        AirlineDAO airlinePersisted = airlineRepository.save(al.toDAO());
        al.ativar();//------------------------ ATIVAR
        String tamanhoLista = String.valueOf(airlineRepository.findAll().size());
        System.out.println("Linha aerea Cadastrada com SUCESSO!" + " - Quantidade de Linhas: " + tamanhoLista);
        System.out.println(al.toString());
        return new ResponseEntity<AirlineDTO>(airlinePersisted.toDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/airlines/{id}/update")
    public ResponseEntity<AirlineDTO> updateAirline(@PathVariable("id") Long id, @RequestBody AirlineDTO al) {
        al.setId(id);
        AirlineDAO airlineUpdated = airlineRepository.save(al.toDAO());
        return new ResponseEntity<AirlineDTO>(airlineUpdated.toDTO(), HttpStatus.OK);
    }

    @GetMapping("/airlines")
    public ResponseEntity<List<AirlineDTO>> getAllAirlines() {
        return ResponseEntity.ok().body(airlineRepository.findAll()
                .stream()
                .map(airlineDAO -> airlineDAO.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/airlines/{id}")
    public ResponseEntity<AirlineDTO> getAirlineById(@PathVariable("id") Long id) {
        Optional<AirlineDAO> airline = airlineRepository.findById(id);
        if (airline.isPresent()) {
            return new ResponseEntity<AirlineDTO>(airline.get().toDTO(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/airlines/{id}/delete")
    public ResponseEntity<AirlineDTO> deleteAirlineById(@PathVariable("id") Long id) {
        AirlineDAO airline = new AirlineDAO();
        airline.inativar(); //--------------------- INATIVAR
        airline.setId(id);
        airlineRepository.delete(airline);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/airlines/{id}/ativar")
    public void ativarAirline(Long id) {
        airlineRepository.findById(id).ifPresent(airline -> {
            airline.ativar();
            airlineRepository.save(airline);
        });
    }

    @PostMapping("/airlines/{id}/inativar")
    public void inativarAirline(Long id) {
        airlineRepository.findById(id).ifPresent(airline -> {
            airline.inativar();
            airlineRepository.save(airline);
        });
    }

    //--------------------------------------- |AIRPORT| -----------------------------------------------------
    @Autowired
    private AirportRepository airportRepository;
    public List<AirportDTO> AirportsList = new ArrayList<AirportDTO>();
    public HashMap<Integer, AirportDTO> airports = new HashMap<Integer, AirportDTO>();

    @PostMapping("/airports")
    public ResponseEntity<AirportDTO> addAirport(@RequestBody @Valid AirportDTO ap) {
        AirportDAO airportPersisted = airportRepository.save(ap.toDAO());
        ap.ativar();//------------------------ ATIVAR
        String tamanhoLista = String.valueOf(airportRepository.findAll().size());
        System.out.println("Linha aerea Cadastrada com SUCESSO!" + " - Quantidade de Linhas: " + tamanhoLista);
        System.out.println(ap.toString());
        return new ResponseEntity<AirportDTO>(airportPersisted.toDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/airports/{id}/update")
    public ResponseEntity<AirportDTO> updateAirport(@PathVariable("id") Long id, @RequestBody AirportDTO ap) {
        ap.setId(id);
        AirportDAO airportUpdated = airportRepository.save(ap.toDAO());
        return new ResponseEntity<AirportDTO>(airportUpdated.toDTO(), HttpStatus.OK);
    }

    @GetMapping("/airports")
    public ResponseEntity<List<AirportDTO>> getAllAirports() {
        return ResponseEntity.ok().body(airportRepository.findAll()
                .stream()
                .map(airportDAO -> airportDAO.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/airports/{id}")
    public ResponseEntity<AirportDTO> getAirportById(@PathVariable("id") Long id) {
        Optional<AirportDAO> airport = airportRepository.findById(id);
        if (airport.isPresent()) {
            return new ResponseEntity<AirportDTO>(airport.get().toDTO(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/airports/{id}/delete")
    public ResponseEntity<AirportDTO> deleteAirportById(@PathVariable("id") Long id) {
        AirportDAO airport = new AirportDAO();
        airport.inativar(); //--------------------- INATIVAR
        airport.setId(id);
        airportRepository.delete(airport);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/airports/{id}/ativar")
    public void ativarAirport(Long id) {
        airportRepository.findById(id).ifPresent(airport -> {
            airport.ativar();
            airportRepository.save(airport);
        });
    }

    @PostMapping("/airports/{id}/inativar")
    public void inativarAirport(Long id) {
        airportRepository.findById(id).ifPresent(airport -> {
            airport.inativar();
            airportRepository.save(airport);
        });
    }

    //--------------------------------------- |TICKETS| -----------------------------------------------------

    @Autowired
    private TicketRepository ticketRepository;
    public List<TicketDTO> TicketsList = new ArrayList<TicketDTO>();
    public HashMap<Integer, TicketDTO> tickets = new HashMap<Integer, TicketDTO>();

    @PostMapping("/tickets")
    public ResponseEntity<TicketDTO> addTicket(@RequestBody @Valid TicketDTO tk) {
        TicketDAO ticketPersisted = ticketRepository.save(tk.toDAO());
        tk.ativar();//------------------------ ATIVAR
        String tamanhoLista = String.valueOf(ticketRepository.findAll().size());
        System.out.println("Passagem cadastrada com SUCESSO!" + " - Quantidade de Passagens: " + tamanhoLista);
        System.out.println(tk.toString());
        return new ResponseEntity<TicketDTO>(ticketPersisted.toDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/tickets/{id}/update")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable("id") Long id, @RequestBody TicketDTO tk) {
        tk.setId(id);
        TicketDAO ticketUpdated = ticketRepository.save(tk.toDAO());
        return new ResponseEntity<TicketDTO>(ticketUpdated.toDTO(), HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok().body(ticketRepository.findAll()
                .stream()
                .map(ticketDAO -> ticketDAO.toDTO())
                .collect(Collectors.toList()));
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable("id") Long id) {
        Optional<TicketDAO> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return new ResponseEntity<TicketDTO>(ticket.get().toDTO(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tickets/{id}/delete")
    public ResponseEntity<TicketDTO> deleteTicketById(@PathVariable("id") Long id) {
        TicketDAO ticket = new TicketDAO();
        ticket.inativar(); //--------------------- INATIVAR
        ticket.setId(id);
        ticketRepository.delete(ticket);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tickets/{id}/ativar")
    public void ativarTicket(Long id) {
        ticketRepository.findById(id).ifPresent(ticket -> {
            ticket.ativar();
            ticketRepository.save(ticket);
        });
    }

    @PostMapping("/tickets/{id}/inativar")
    public void inativarTicket(Long id) {
        ticketRepository.findById(id).ifPresent(ticket -> {
            ticket.inativar();
            ticketRepository.save(ticket);
        });
    }
}