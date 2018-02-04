import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { EllaFinanceFooModule } from './foo/foo.module';
import { EllaFinanceAnnounceReportTaskModule } from './announce-report-task/announce-report-task.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        EllaFinanceFooModule,
        EllaFinanceAnnounceReportTaskModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EllaFinanceEntityModule {}
