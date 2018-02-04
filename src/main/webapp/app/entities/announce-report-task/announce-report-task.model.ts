import { BaseEntity } from './../../shared';

export class AnnounceReportTask implements BaseEntity {
    constructor(
        public id?: string,
        public title?: string,
        public year?: number,
        public companyListContentType?: string,
        public companyList?: any,
    ) {
    }
}
