import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { AnnounceReportTask } from './announce-report-task.model';
import { AnnounceReportTaskPopupService } from './announce-report-task-popup.service';
import { AnnounceReportTaskService } from './announce-report-task.service';

@Component({
    selector: 'jhi-announce-report-task-dialog',
    templateUrl: './announce-report-task-dialog.component.html'
})
export class AnnounceReportTaskDialogComponent implements OnInit {

    announceReportTask: AnnounceReportTask;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private announceReportTaskService: AnnounceReportTaskService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.announceReportTask.id !== undefined) {
            this.subscribeToSaveResponse(
                this.announceReportTaskService.update(this.announceReportTask));
        } else {
            this.subscribeToSaveResponse(
                this.announceReportTaskService.create(this.announceReportTask));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<AnnounceReportTask>>) {
        result.subscribe((res: HttpResponse<AnnounceReportTask>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: AnnounceReportTask) {
        this.eventManager.broadcast({ name: 'announceReportTaskListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-announce-report-task-popup',
    template: ''
    // templateUrl: './announce-report-task-dialog.component.html'
})
export class AnnounceReportTaskPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private announceReportTaskPopupService: AnnounceReportTaskPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.announceReportTaskPopupService
                    .open(AnnounceReportTaskDialogComponent as Component, params['id']);
            } else {
                this.announceReportTaskPopupService
                    .open(AnnounceReportTaskDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
