import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-service-type-list',
  imports: [],
  templateUrl: './service-type-list.html',
  styleUrl: './service-type-list.css',
})
export class ServiceTypeList implements OnInit {
  serviceTypes: any[] = [];
  loading: boolean = false;
  error: string | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchServiceTypes();
  }

  fetchServiceTypes(): void {
    this.loading = true;
    this.error = null;

    this.http.get('/api/service-types').subscribe({
      next: (data: any) => {
        this.serviceTypes = data.data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error fetching service types: ' + err.message;
        this.loading = false;
      },
    });
  }
}
