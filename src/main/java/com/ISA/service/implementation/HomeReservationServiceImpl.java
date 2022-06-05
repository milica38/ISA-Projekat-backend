package com.ISA.service.implementation;

import com.ISA.domain.dto.HomeHistoryReservationDTO;
import com.ISA.domain.dto.HomeReservationDTO;
import com.ISA.domain.model.*;
import com.ISA.repository.*;
import com.ISA.service.definition.EmailService;
import com.ISA.service.definition.HomeReservationService;
import com.ISA.service.definition.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class HomeReservationServiceImpl implements HomeReservationService {

    @Autowired
    private HomeReservationRepository homeReservationRepository;

    @Autowired
    LoyalProgrammeRepository loyalProgrammeRepository;

    @Autowired
    private HomeProfileRepository homeProfileRepository;

    @Autowired
    private HomeFreeTermsRepository homeFreeTermsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public HomeReservation add(HomeReservationDTO dto) throws Exception{

        HomeProfile homeProfile = homeProfileRepository.findById(dto.getHouseId()).get();
        User currentUser = userService.getCurrentUser();
        User homeOwner = userService.getUserById(homeProfile.getownerId());
        Optional<LoyalProgramme> lp = loyalProgrammeRepository.findById(1);

        if(isOverlapping(homeProfile.getId(), dto.getStartDate(), dto.getEndDate())){
          return null;
        }

        if(!canClientBook(userService.getCurrentUser().getId(), dto.getHouseId(), dto.getStartDate(), dto.getEndDate() )){
            return null;
        }

        if(currentUser.getPenalty() >= 3){
            throw new Exception("Number of penalties is 3 or more");

        }

        long diffInMillies = Math.abs(dto.getEndDate().getTime() - dto.getStartDate().getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        HomeReservation reservation = new HomeReservation();
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setHomeProfile(homeProfile);
        reservation.setClientId(currentUser.getId());
        reservation.setNumberOfPeople(homeProfile.getNumberOfBeds());
        reservation.setExtraServices(dto.getExtraServices());
        HomeFreeTerms freeTerm = getDates(reservation.getHomeProfile().getId(), reservation.getStartDate(), reservation.getEndDate());


        if(currentUser.getNumberOfPoints()<lp.get().getSilverPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice(freeTerm.getActionPrice() * days );
            }
            else {
                reservation.setPrice(homeProfile.getPricelist() * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  homeProfile.getExtraPrice() * days);
            }
            currentUser.setCategory("Regular");
        }

        if(currentUser.getNumberOfPoints()>=lp.get().getSilverPoints() && currentUser.getNumberOfPoints()<lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice() - (freeTerm.getActionPrice()*(lp.get().getSilverAction())/100)) * days );
            }
            else {
                reservation.setPrice((homeProfile.getPricelist()-(homeProfile.getPricelist()*(lp.get().getSilverAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  (homeProfile.getExtraPrice()-(homeProfile.getExtraPrice()*(lp.get().getSilverAction())/100)) * days);
            }
            currentUser.setCategory("Silver");
        }

        if(currentUser.getNumberOfPoints()>=lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice() - (freeTerm.getActionPrice()*(lp.get().getGoldAction())/100)) * days );
            }
            else {
                reservation.setPrice((homeProfile.getPricelist()-(homeProfile.getPricelist()*(lp.get().getGoldAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  (homeProfile.getExtraPrice()-(homeProfile.getExtraPrice()*(lp.get().getGoldAction())/100)) * days);
            }
            currentUser.setCategory("Gold");
        }

        if(homeOwner.getNumberOfPoints()<lp.get().getSilverPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice(freeTerm.getActionPrice() * days );
            }
            else {
                reservation.setPrice(homeProfile.getPricelist() * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  homeProfile.getExtraPrice() * days);
            }
            homeOwner.setCategory("Regular");
        }

        if(homeOwner.getNumberOfPoints()>=lp.get().getSilverPoints() && homeOwner.getNumberOfPoints()<lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice() + (freeTerm.getActionPrice()*(lp.get().getSilverAction())/100)) * days );
            }
            else {
                reservation.setPrice((homeProfile.getPricelist()+(homeProfile.getPricelist()*(lp.get().getSilverAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() +  (homeProfile.getExtraPrice()+(homeProfile.getExtraPrice()*(lp.get().getSilverAction())/100)) * days);
            }
            homeOwner.setCategory("Silver");
        }

        if(homeOwner.getNumberOfPoints()>=lp.get().getGoldPoints()){
            if(freeTerm != null && freeTerm.isAction() ){
                reservation.setPrice((freeTerm.getActionPrice() + (freeTerm.getActionPrice()*(lp.get().getGoldAction())/100)) * days );
            }
            else {
                reservation.setPrice((homeProfile.getPricelist()+(homeProfile.getPricelist()*(lp.get().getGoldAction())/100)) * days);
            }

            if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
                reservation.setPrice(reservation.getPrice() + (homeProfile.getExtraPrice()+(homeProfile.getExtraPrice()*(lp.get().getGoldAction())/100)) * days);
            }
            homeOwner.setCategory("Gold");
        }

        emailService.sendEmailForHouseReservation(currentUser, reservation);
        currentUser.setNumberOfPoints(currentUser.getNumberOfPoints()+ lp.get().getReservationPoints());
        homeOwner.setNumberOfPoints(homeOwner.getNumberOfPoints()+ lp.get().getReservedPoints());
        return homeReservationRepository.save(reservation);
    }

    private List<HomeFreeTerms> getFree(List<HomeFreeTerms> allTerms, Date startDate, Date endDate) {
        List<HomeFreeTerms> terms = new ArrayList<>();

        for(HomeFreeTerms term: allTerms) {
            if((startDate.after(term.getStartDate()) || isDateEqual(startDate, term.getStartDate())) && (endDate.before(term.getEndDate()) || isDateEqual(endDate, term.getEndDate()))) {
                terms.add(term);
            }
        }
        return terms;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public HomeReservation addByOwner(HomeReservationDTO dto, Long clientId) throws Exception {
        HomeProfile homeProfile = homeProfileRepository.findById(dto.getHouseId()).get();
        List<HomeFreeTerms> freeTerms = homeFreeTermsRepository.findAllByHomeProfileId(dto.getHouseId());

        freeTerms = getFree(freeTerms, dto.getStartDate(), dto.getEndDate());

        if(isOverlapping(homeProfile.getId(), dto.getStartDate(), dto.getEndDate())){
            return null;
        }
        if(freeTerms.isEmpty()){
            return null;
        }

        long diffInMillies = Math.abs(dto.getEndDate().getTime() - dto.getStartDate().getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        HomeReservation reservation = new HomeReservation();
        reservation.setCancelled(false);
        reservation.setEndDate(dto.getEndDate());
        reservation.setStartDate(dto.getStartDate());
        reservation.setHomeProfile(homeProfile);
        reservation.setNumberOfPeople(homeProfile.getNumberOfBeds());
        reservation.setClientId(dto.getClientId());

        HomeFreeTerms freeTerm = getDates(reservation.getHomeProfile().getId(), reservation.getStartDate(), reservation.getEndDate());
        if(freeTerm != null && freeTerm.isAction()){
            reservation.setPrice(freeTerm.getActionPrice() * days );
        }
        else {
            reservation.setPrice(homeProfile.getPricelist() * days);
        }

        if(reservation.getExtraServices() != null && !reservation.getExtraServices().equals("No extra service")) {
            reservation.setPrice(reservation.getPrice() +  homeProfile.getExtraPrice() * days);
        }

        List<User> clients = userRepository.findAllByType("Client");
        for(User client: clients){
            if(reservation.getClientId().equals(client.getId())){
                emailService.sendEmailForHouseReservation(client, reservation);
            }
        }
        return homeReservationRepository.save(reservation);
    }
  
    public HomeFreeTerms getDates(Long houseId, Date startDate, Date endDate) {
        List<HomeFreeTerms> freeTerms = homeFreeTermsRepository.findAllByHomeProfileId(houseId);
        HomeFreeTerms result = null;

        for (HomeFreeTerms term : freeTerms) {
            if (term.getHomeProfile().getId().equals(houseId) && (isDateEqual(term.getStartDate(), startDate) || term.getStartDate().before(startDate)) && (isDateEqual(term.getEndDate(), endDate) || term.getEndDate().after(endDate)))
                result = term;

        }
        return result;
    }


    @Override
    public List<HomeProfile> findAll() {

        return homeProfileRepository.findAll();
    }

    @Override
    public boolean isOverlapping(long houseId, Date startDate, Date endDate){

        List<HomeReservation> reservations = homeReservationRepository.findAll();

        for(HomeReservation reservation: reservations){

            if(reservation.getCancelled()) {
                continue;
            }

           if((isDateEqual(startDate, reservation.getStartDate()) || isDateEqual(endDate, reservation.getEndDate()) || (isDateEqual(startDate, reservation.getEndDate())) ||  (isDateEqual(endDate, reservation.getStartDate()))) && reservation.getHomeProfile().getId().equals(houseId)) {
                return true;
            }

            if(startDate.after(reservation.getStartDate()) && startDate.before(reservation.getEndDate()) && reservation.getHomeProfile().getId().equals(houseId)){
                return true;
            }

            if(endDate.after(reservation.getStartDate()) && endDate.before(reservation.getEndDate()) && reservation.getHomeProfile().getId().equals(houseId)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<HomeReservation> getMyReservations() {
        User user = userService.getCurrentUser();

        return homeReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
    }


    @Override
    public boolean cancel(Long id) {
        Optional<HomeReservation> reservation = homeReservationRepository.findById(id);

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(reservation.get().getStartDate());
        calendar.add(Calendar.DATE, -3);
        Date lastDayToCancel = calendar.getTime();

        if(lastDayToCancel.before(today))
           return false;

        reservation.get().setCancelled(true);

        homeReservationRepository.save(reservation.get());
        return true;
    }

    @Override
    public List<HomeFreeTerms> getAllHousesOnAction(){

        return homeFreeTermsRepository.findAllByIsAction(true);
    }

    @Override
    public boolean canClientBook(Long currentClientId, Long houseId, Date startDate, Date endDate) {
        List<HomeReservation> reservations = homeReservationRepository.findAll();

        for(HomeReservation reservation: reservations){
            System.out.println("==============================");
            System.out.println(reservation.getId());
            System.out.println(reservation.getCancelled());
            System.out.println(reservation.getHomeProfile().getId().equals(houseId));
            System.out.println(reservation.getClientId().equals(currentClientId));
            System.out.println(isDateEqual(reservation.getStartDate(), startDate));
            System.out.println(isDateEqual(reservation.getEndDate(), endDate));

            if(reservation.getCancelled() && reservation.getHomeProfile().getId().equals(houseId) && reservation.getClientId().equals(currentClientId) && isDateEqual(reservation.getStartDate(), startDate) && isDateEqual(reservation.getEndDate(), endDate)){
                return false;
            }

        }
        return true;
    }

    @Override
    public List<HomeReservation> getAllReservationsForMyHouses(HomeHistoryReservationDTO dto) {

        List<HomeReservation> all = homeReservationRepository.findAll();
        List<HomeReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(HomeReservation hr : all) {
            if(hr.getHomeProfile().getownerId().equals(currentUser.getId()) && hr.getStartDate().after(today)) {
                results.add(hr);
            }
        }

        return results;
    }

    @Override
    public List<HomeReservation> getAllHistoryReservationsForMyHouses(HomeHistoryReservationDTO dto) {

        List<HomeReservation> all = homeReservationRepository.findAll();
        List<HomeReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();

        for(HomeReservation hr : all) {
            calendar.setTime(hr.getEndDate());
            calendar.add(Calendar.DATE, 1);
            Date lastDay = calendar.getTime();
            if(hr.getHomeProfile().getownerId().equals(currentUser.getId()) && lastDay.before(today)) {
                results.add(hr);
            }
        }
        return results;
    }

    @Override
    public List<HomeReservation> getAllTodayReservationsForMyHouses(HomeHistoryReservationDTO dto) {

        List<HomeReservation> all = homeReservationRepository.findAll();
        List<HomeReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(HomeReservation hr : all) {
            if(hr.getHomeProfile().getownerId().equals(currentUser.getId()) && (
                    (hr.getStartDate().before(today) && hr.getEndDate().after(today)) || (hr.getStartDate().before(today) &&  isDateEqual(hr.getEndDate(), today))
                    || (isDateEqual(hr.getStartDate(), today) && hr.getEndDate().after(today)) || (isDateEqual(hr.getStartDate(), today) &&  isDateEqual(hr.getEndDate(), today))
                    )
            ) {
                results.add(hr);
            }
        }
        return results;
    }

    @Override
    public List<HomeReservation> getAllReservationsForCharts(HomeHistoryReservationDTO dto) {

        List<HomeReservation> all = homeReservationRepository.findAll();
        List<HomeReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(HomeReservation hr : all) {
            if(hr.getHomeProfile().getownerId().equals(currentUser.getId())) {
                results.add(hr);
            }
        }
        return results;
    }

    public List<HomeReservation> getAllHomeReservationsForCharts(HomeHistoryReservationDTO dto) {

        List<HomeReservation> all = homeReservationRepository.findAll();
        List<HomeReservation> results = new ArrayList<>();
        User currentUser = userService.getCurrentUser();
        Date today = new Date();

        for(HomeReservation hr : all) {

                results.add(hr);

        }
        return results;
    }

    @Override
    public List<HomeReservation> getAll() {
        return homeReservationRepository.findAll();
    }

    @Override
    public List<HomeReservation> getAllReservations(Long ownerId, Long houseId) {

        List<HomeReservation> reservations = homeReservationRepository.getAllByHomeProfileId(houseId);
        User currentUser = userService.getCurrentUser();
        List<HomeReservation> results = new ArrayList<>();

        for (HomeReservation reservation : reservations) {
            if (reservation.getHomeProfile().getId().equals(houseId) && reservation.getHomeProfile().getownerId().equals(currentUser.getId())) {
                results.add(reservation);
            }
        }
        return results;
    }

    public HomeReservation get(Long id) {
        return homeReservationRepository.findById(id).get();
    }

    @Override
    public List<HomeReservation> getMyFinishedReservations() {
        User user = userService.getCurrentUser();
        List<HomeReservation> reservations = homeReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<HomeReservation> results = new ArrayList<>();
        Date today = new Date();


        for(HomeReservation reservation: reservations){
            if(reservation.getEndDate().before(today)){
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<HomeReservation> getMyUpcomingReservations() {
        User user = userService.getCurrentUser();
        List<HomeReservation> reservations = homeReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<HomeReservation> results = new ArrayList<>();
        Date today = new Date();

        for(HomeReservation reservation: reservations){
            if(reservation.getStartDate().after(today)){
                results.add(reservation);
            }
        }
        return results;
    }

    @Override
    public List<HomeReservation> getMyInProgressReservations() {
        User user = userService.getCurrentUser();
        List<HomeReservation> reservations = homeReservationRepository.getAllByClientIdAndCancelled(user.getId(), false);
        List<HomeReservation> results = new ArrayList<>();
        Date today = new Date();

        for(HomeReservation reservation: reservations){
            if((reservation.getStartDate().before(today) || isDateEqual(reservation.getStartDate(), today)) && (reservation.getEndDate().after(today) || isDateEqual(reservation.getEndDate(), today))){
                results.add(reservation);
            }
        }
        return results;
    }

    public boolean isDateEqual(Date date1, Date date2) {

        return date1.getDay() == date2.getDay() && date1.getYear() == date2.getYear() && date1.getMonth() == date2.getMonth();

    }
}
