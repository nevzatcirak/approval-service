package me.nevzatcirak.service.approval.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.nevzatcirak.service.approval.api.model.ApprovalProcess;
import me.nevzatcirak.service.approval.api.model.Approver;
import me.nevzatcirak.service.approval.controller.rest.model.CreateApprovalRequest;
import me.nevzatcirak.service.approval.controller.rest.model.ProcessRequestState;
import me.nevzatcirak.service.approval.controller.rest.model.StateDetailUpdateRequest;
import me.nevzatcirak.service.approval.controller.rest.model.QueryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Nevzat ÇIRAK
 * @mail nevzatcirak17@gmail.com
 * Created by nevzatcirak at 07/12/2021
 */
@RequestMapping("/api/process")
@Api(value = "Approval Process Api Documentation")
public interface ProcessController {
    /**
     * Creates new approval process by using Service type, Document Id and Approver Info
     *
     * @param request
     * @return ApprovalProcess
     */
    @PostMapping("/create")
    @ApiOperation(value = "Create Approval Process", notes = "New process adding method")
    ResponseEntity<ApprovalProcess> createApprovalProcess(
            @ApiParam("Approval Process Initial Request Data")
            @RequestBody CreateApprovalRequest request);

    /**
     * Update a process detail state by using username and status
     *
     * @param documentType
     * @param documentId
     * @param stateDetailUpdateRequest
     * @return ApprovalProcess
     */
    @PutMapping("/{documentType}/{documentId}/update")
    @ApiOperation(value = "Update Process State", notes = "Process info updating method by using document type and document id")
    ResponseEntity<ApprovalProcess> updateProcessState(
            @ApiParam("Process Document/Service Name") @PathVariable String documentType,
            @ApiParam("Process Document Id") @PathVariable String documentId,
            @ApiParam("Process Update State Request Data") @RequestBody StateDetailUpdateRequest stateDetailUpdateRequest);

    /**
     * Update a process detail state by using username and status
     *
     * @param processId
     * @param stateDetailUpdateRequest
     * @return ApprovalProcess
     */
    @PutMapping("/{processId}/update")
    @ApiOperation(value = "Update Process State", notes = "Process info updating method")
    ResponseEntity<ApprovalProcess> updateProcessState(@ApiParam("Process ID") @PathVariable Long processId,
                                                       @ApiParam("Process Update State Request Data")
                                                       @RequestBody StateDetailUpdateRequest stateDetailUpdateRequest);

    /**
     * Update a process detail state by using username and status
     *
     * @param documentType
     * @param documentId
     * @return ApprovalProcess
     */
    @PutMapping("/{documentType}/{documentId}/cancel")
    @ApiOperation(value = "Cancel Related Process", notes = "Process info cancelling method by using document type and document id")
    ResponseEntity<ApprovalProcess> cancelProcess(
            @ApiParam("Process Document/Service Name") @PathVariable String documentType,
            @ApiParam("Process Document Id") @PathVariable String documentId);

    /**
     * Update a process detail state by using username and status
     *
     * @param processId
     * @return ApprovalProcess
     */
    @PutMapping("/{processId}/cancel")
    @ApiOperation(value = "Cancel Related Process", notes = "Process info cancelling method by using process id")
    ResponseEntity<ApprovalProcess> cancelProcess(@ApiParam("Process ID") @PathVariable Long processId);

    /**
     * Query type statuses by filtering id list
     *
     * @param documentType
     * @param queryRequest contains document type and set of document ids
     * @return Set<ApprovalProcess>
     */
    @PostMapping("/query/{documentType}")
    @ApiOperation(value = "Query Process State", notes = "Process info querying method by using document type")
    ResponseEntity<Set<ApprovalProcess>> queryProcessStatus(@ApiParam("Process Document/Service Name") @PathVariable String documentType,
                                                            @ApiParam("Process Query Request Data") @RequestBody QueryRequest queryRequest);

    /**
     * Query type statuses by filtering id list
     *
     * @param documentType
     * @param username
     * @param queryRequest contains document type and set of document ids
     * @return Set<ApprovalProcess>
     */
    @PostMapping("/query/{documentType}/{username}")
    @ApiOperation(value = "Query User's Process State", notes = "Process info querying method by using document type and username")
    ResponseEntity<Set<ApprovalProcess>> queryProcessStatusByUsername(@ApiParam("Process Document/Service Name") @PathVariable String documentType,
                                                                      @ApiParam("Process Approver Username") @PathVariable String username,
                                                                      @ApiParam("Process Query Request Data") @RequestBody QueryRequest queryRequest);

    /**
     * Query type statuses by filtering id list
     *
     * @param documentType
     * @param username
     * @param queryRequest contains document type and set of document ids
     * @return Set<ApprovalProcess>
     */
    @PostMapping("/query/{documentType}/{username}/next")
    @ApiOperation(value = "Query Next Approver's Process State", notes = "Process info querying method by using document type and username who is next approver")
    ResponseEntity<Set<ApprovalProcess>> queryProcessStatusByNextApproverUsername(@ApiParam("Process Document/Service Name") @PathVariable String documentType,
                                                                                  @ApiParam("Process Approver Username") @PathVariable String username,
                                                                                  @ApiParam("Process Query Request Data") @RequestBody QueryRequest queryRequest);

    /**
     * Query type statuses by filtering id list and eligible username
     *
     * @param documentType
     * @param username
     * @param queryRequest
     * @return
     */
    @PostMapping("/query/{documentType}/{username}/eligible")
    @ApiOperation(value = "Query Eligible User's Process State", notes = "Process info querying method by using document type and username who is eligible. " +
            "Eligible means that User should be creator or already approved/rejected or next approver")
    ResponseEntity<Set<ApprovalProcess>> queryProcessStatusByEligibleUser(@ApiParam("Process Document/Service Name") @PathVariable String documentType,
                                                                          @ApiParam("Process Approver Username") @PathVariable String username,
                                                                          @ApiParam("Process Query Request Data") @RequestBody QueryRequest queryRequest);

    /**
     * Gets process list by filtering final status
     *
     * @param documentType
     * @param status
     * @return List of ApprovalProcess
     */
    @GetMapping("/{documentType}/{status}")
    @ApiOperation(value = "Retrieves Process State Info", notes = "Process info retrieving method by using document type and process status")
    ResponseEntity<Set<ApprovalProcess>> getApprovalProcesses(@ApiParam("Process Document/Service Name") @PathVariable String documentType,
                                                              @ApiParam("Process State") @PathVariable ProcessRequestState status);

    /**
     * Gets process by filtering document id and type
     *
     * @param documentType
     * @param documentId
     * @return List of ApprovalProcess
     */
    @GetMapping("/{documentType}/{documentId}/state")
    @ApiOperation(value = "Retrieves Process State Info", notes = "Process info retrieving method by using document type and document id")
    ResponseEntity<ApprovalProcess> getApprovalProcess(@ApiParam("Process Document/Service Name") @PathVariable String documentType,
                                                       @ApiParam("Process Document Id") @PathVariable String documentId);

    /**
     * Gets process by filtering processId
     *
     * @param processId
     * @return ApprovalProcess
     */
    @GetMapping("/{processId}/state")
    @ApiOperation(value = "Retrieves Process State Info", notes = "Process info retrieving method by using process id")
    ResponseEntity<ApprovalProcess> getApprovalProcess(@ApiParam("Process ID") @PathVariable Long processId);


    /**
     * Gets next approver data of defined process
     *
     * @param documentType
     * @param documentId
     * @return Approver
     */
    @GetMapping("/{documentType}/{documentId}/next")
    @ApiOperation(value = "Retrieves Process Next Approver Info", notes = "Process next approver info retrieving method by using document type and document id")
    ResponseEntity<Approver> getNextApprover(@ApiParam("Process Document/Service Name") @PathVariable String documentType,
                                             @ApiParam("Process Document Id") @PathVariable String documentId);

    /**
     * Gets next approver data of defined process
     *
     * @param processId
     * @return Approver
     */
    @GetMapping("/{processId}/next")
    @ApiOperation(value = "Retrieves Process Next Approver Info", notes = "Process next approver info retrieving method by using process id")
    ResponseEntity<Approver> getNextApprover(@ApiParam("Process ID") @PathVariable Long processId);


}
