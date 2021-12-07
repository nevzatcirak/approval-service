package me.nevzatcirak.service.approval.controller;

import me.nevzatcirak.service.approval.controller.model.StateDetailUpdateRequest;
import me.nevzatcirak.service.approval.controller.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.model.StatusQueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nevzat ÇIRAK
 * @mail ncirak@havelsan.com.tr
 * Created by ncirak at 07/12/2021
 */
@RequestMapping("/rest/process")
public interface ProcessController {
    /**
     * Creates new approval process by using Service type, Document Id and Approver Info
     *
     * @param request
     * @return
     */
    @PostMapping("/create")
    ResponseEntity<?> createApprovalProcess(@RequestBody CreateApprovalRequest request);

    /**
     * Update a process detail state by using username and status
     *
     * @param type
     * @param documentId
     * @param stateDetailUpdateRequest
     * @return Process
     */
    @PutMapping("/state/update/{type}/{documentId}")
    ResponseEntity<?> updateProcessState(@PathVariable String type,
                                         @PathVariable String documentId,
                                         @RequestBody StateDetailUpdateRequest stateDetailUpdateRequest);

    /**
     * Query type statuses by filtering id list
     *
     * @param queryRequest
     * @return Map<ID, ProcessState>
     */
    @PostMapping("/query-status")
    ResponseEntity<?> queryProcessStatus(@RequestBody StatusQueryRequest queryRequest);

    /**
     * Update a process detail state by using username and status
     *
     * @param processId
     * @param stateDetailUpdateRequest
     * @return Process
     */
    @PutMapping("/state/update/{processId}")
    ResponseEntity<?> updateProcessState(@PathVariable String processId,
                                         @RequestBody StateDetailUpdateRequest stateDetailUpdateRequest);

    /**
     * Gets process list by filtering final status
     *
     * @param type
     * @param documentId
     * @param status
     * @return List of Process
     */
    @GetMapping("/state/{type}/{documentId}/{status}")
    ResponseEntity<?> getApprovalProcesses(@PathVariable String type,
                                           @PathVariable String documentId,
                                           @PathVariable ProcessRequestState status);

    /**
     * Gets process list by filtering final status
     *
     * @param processId
     * @param status
     * @return List of Process
     */
    @GetMapping("/state/{processId}/{status}")
    ResponseEntity<?> getApprovalProcesses(@PathVariable String processId,
                                           @PathVariable ProcessRequestState status);

    /**
     * Gets next approver data of defined process
     *
     * @param type
     * @param documentId
     * @return Approver Username
     */
    @GetMapping("/next/approver/{type}/{documentId}")
    ResponseEntity<?> getNextApprover(@PathVariable String type,
                                      @PathVariable String documentId);

    /**
     * Gets next approver data of defined process
     *
     * @param processId
     * @return Approver Username
     */
    @GetMapping("/next/approver/{processId}")
    ResponseEntity<?> getNextApprover(@PathVariable String processId);


}
