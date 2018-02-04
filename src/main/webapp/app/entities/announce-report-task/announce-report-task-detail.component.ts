import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { AnnounceReportTask } from './announce-report-task.model';
import { AnnounceReportTaskService } from './announce-report-task.service';

@Component({
    selector: 'jhi-announce-report-task-detail',
    templateUrl: './announce-report-task-detail.component.html'
})
export class AnnounceReportTaskDetailComponent implements OnInit, OnDestroy {

    announceReportTask: AnnounceReportTask;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private announceReportTaskService: AnnounceReportTaskService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAnnounceReportTasks();
    }

    load(id) {
        this.announceReportTaskService.find(id)
            .subscribe((announceReportTaskResponse: HttpResponse<AnnounceReportTask>) => {
                this.announceReportTask = announceReportTaskResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAnnounceReportTasks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'announceReportTaskListModification',
            (response) => this.load(this.announceReportTask.id)
        );
    }
}
