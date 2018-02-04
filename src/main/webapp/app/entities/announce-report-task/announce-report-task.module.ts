import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EllaFinanceSharedModule } from '../../shared';
import {
    AnnounceReportTaskService,
    AnnounceReportTaskPopupService,
    AnnounceReportTaskComponent,
    AnnounceReportTaskDetailComponent,
    AnnounceReportTaskDialogComponent,
    AnnounceReportTaskPopupComponent,
    AnnounceReportTaskDeletePopupComponent,
    AnnounceReportTaskDeleteDialogComponent,
    announceReportTaskRoute,
    announceReportTaskPopupRoute,
    AnnounceReportTaskResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...announceReportTaskRoute,
    ...announceReportTaskPopupRoute,
];

@NgModule({
    imports: [
        EllaFinanceSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AnnounceReportTaskComponent,
        AnnounceReportTaskDetailComponent,
        AnnounceReportTaskDialogComponent,
        AnnounceReportTaskDeleteDialogComponent,
        AnnounceReportTaskPopupComponent,
        AnnounceReportTaskDeletePopupComponent,
    ],
    entryComponents: [
        AnnounceReportTaskComponent,
        AnnounceReportTaskDialogComponent,
        AnnounceReportTaskPopupComponent,
        AnnounceReportTaskDeleteDialogComponent,
        AnnounceReportTaskDeletePopupComponent,
    ],
    providers: [
        AnnounceReportTaskService,
        AnnounceReportTaskPopupService,
        AnnounceReportTaskResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EllaFinanceAnnounceReportTaskModule {}
