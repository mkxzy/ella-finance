import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { AnnounceReportTask } from './announce-report-task.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<AnnounceReportTask>;

@Injectable()
export class AnnounceReportTaskService {

    private resourceUrl =  SERVER_API_URL + 'api/announce-report-tasks';

    constructor(private http: HttpClient) { }

    create(announceReportTask: AnnounceReportTask): Observable<EntityResponseType> {
        const copy = this.convert(announceReportTask);
        return this.http.post<AnnounceReportTask>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(announceReportTask: AnnounceReportTask): Observable<EntityResponseType> {
        const copy = this.convert(announceReportTask);
        return this.http.put<AnnounceReportTask>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<AnnounceReportTask>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<AnnounceReportTask[]>> {
        const options = createRequestOption(req);
        return this.http.get<AnnounceReportTask[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<AnnounceReportTask[]>) => this.convertArrayResponse(res));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: AnnounceReportTask = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<AnnounceReportTask[]>): HttpResponse<AnnounceReportTask[]> {
        const jsonResponse: AnnounceReportTask[] = res.body;
        const body: AnnounceReportTask[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to AnnounceReportTask.
     */
    private convertItemFromServer(announceReportTask: AnnounceReportTask): AnnounceReportTask {
        const copy: AnnounceReportTask = Object.assign({}, announceReportTask);
        return copy;
    }

    /**
     * Convert a AnnounceReportTask to a JSON which can be sent to the server.
     */
    private convert(announceReportTask: AnnounceReportTask): AnnounceReportTask {
        const copy: AnnounceReportTask = Object.assign({}, announceReportTask);
        return copy;
    }
}
