import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    duration: '1m',
    vus: 100,
    thresholds: {
        http_req_failed: ['rate<0.01'], // http errors should be less than 1%
        http_req_duration: ['p(95)<500'], // 95 percent of response times must be below 500ms
      },
  };

export default function () {
  http.get('http://stefan.fontyssemester6individueel.nl/gateway/producer/static');
  sleep(1);
}