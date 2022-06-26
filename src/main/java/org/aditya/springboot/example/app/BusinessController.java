package org.aditya.springboot.example.app;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/business")
public class BusinessController {
    private static final Logger logger = LoggerFactory.getLogger("BusinessController.class");
    private BusinessRepository businessRepository;
    public BusinessController(BusinessRepository businessRepository){

        this.businessRepository = businessRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Business business){
        logger.info("Post Business Request Received:"+new Date());
        Business businessObj = businessRepository.save(business);
        logger.info("New Business Object "+businessObj+" created at:"+new Date());
        return new ResponseEntity(businessObj, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity list(){
        Iterable<Business> businesses = businessRepository.findAll();
        logger.info("All Businesses retrieved\n"+businesses);
        logger.info("All Retrieval Time: "+new Date());
        return new ResponseEntity<>(businessRepository.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable Long id){
        Optional<Business> business = businessRepository.findById(id);
        if(business.isPresent()){
            logger.info("Received Business with id:"+id+" for Delete");
            businessRepository.delete(business.get());
            logger.info("Business with ID:"+id+" is deleted");
            return new ResponseEntity("Business with ID:"+id+" is deleted",HttpStatus.OK);
        }else{
            logger.info(" Business with id:"+id+" not available for Delete");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Business businessObj){
        Optional<Business> business = businessRepository.findById(id);
        if(business.isPresent()){
            logger.info("Received Business with id:"+id+" for Update");
            businessObj.setId(business.get().getId());
            businessRepository.save(businessObj);
            logger.info("Business with ID:"+id+" is updated\n"+businessObj);
            return new ResponseEntity(businessObj,HttpStatus.OK);
        }else{
            logger.info(" Business with id:"+id+" not available for Update");
            return new ResponseEntity(" Business with id:"+id+" not available",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable Long id){
        Optional<Business> business = businessRepository.findById(id);
        if(business.isPresent()){
            logger.info("Received Business with id:"+id+"\n"+business.get());
            return new ResponseEntity(business.get(),HttpStatus.OK);
        }else{
            logger.info(" Business with id:"+id+" not available");
            return new ResponseEntity(" Business with id:"+id+" not available",HttpStatus.NOT_FOUND);
        }
    }
}
