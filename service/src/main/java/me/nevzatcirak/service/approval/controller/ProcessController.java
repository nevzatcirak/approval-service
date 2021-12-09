package me.nevzatcirak.service.approval.controller;

import me.nevzatcirak.service.approval.controller.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.model.StateDetailUpdateRequest;
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
     * @return ApprovalProcess
     */
    @PostMapping("/create")
    ResponseEntity<?> createApprovalProcess(@RequestBody CreateApprovalRequest request);

    /**
     * Update a process detail state by using username and status
     *
     * @param type
     * @param documentId
     * @param stateDetailUpdateRequest
     * @return ApprovalProcess
     */
    @PutMapping("/{type}/{documentId}/update")
    ResponseEntity<?> updateProcessState(@PathVariable String type,
                                         @PathVariable String documentId,
                                         @RequestBody StateDetailUpdateRequest stateDetailUpdateRequest);

    /**
     * Update a process detail state by using username and status
     *
     * @param processId
     * @param stateDetailUpdateRequest
     * @return ApprovalProcess
     */
    @PutMapping("/{processId}/update")
    ResponseEntity<?> updateProcessState(@PathVariable Long processId,
                                         @RequestBody StateDetailUpdateRequest stateDetailUpdateRequest);

    /**
     * Query type statuses by filtering id list
     *
     * @param queryRequest contains document type and set of document ids
     * @return Map<DocumentId, ProcessState>
     */
    @PostMapping("/query-status")
    ResponseEntity<?> queryProcessStatus(@RequestBody StatusQueryRequest queryRequest);

    /**
     * Gets process list by filtering final status
     *
     * @param type
     * @param status
     * @return List of ApprovalProcess
     */
    @GetMapping("/{type}/{status}")
    ResponseEntity<?> getApprovalProcesses(@PathVariable String type,
                                           @PathVariable ProcessRequestState status);

    /**
     * Gets process by filtering document id and type
     *
     * @param type
     * @param id
     * @return List of ApprovalProcess
     */
    @GetMapping("/{type}/{id}/state")
    ResponseEntity<?> getApprovalProcess(@PathVariable String type,
                                           @PathVariable String id);

    /**
     * Gets process by filtering processId
     *
     * @param processId
     * @return ApprovalProcess
     */
    @GetMapping("/{processId}/state")
    ResponseEntity<?> getApprovalProcess(@PathVariable Long processId);


    /**
     * Gets next approver data of defined process
     *
     * @param type
     * @param documentId
     * @return ApproverSummary
     */
    @GetMapping("/{type}/{documentId}/next")
    ResponseEntity<?> getNextApprover(@PathVariable String type,
                                      @PathVariable String documentId);

    /**
     * Gets next approver data of defined process
     *
     * @param processId
     * @return ApproverSummary
     */
    @GetMapping("/{processId}/next")
    ResponseEntity<?> getNextApprover(@PathVariable Long processId);


}
