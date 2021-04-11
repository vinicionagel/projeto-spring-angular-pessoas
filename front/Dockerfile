FROM nginx:1.19.1-alpine

RUN rm -rf /usr/share/nginx/html/*

COPY dist/angular-frontend /usr/share/nginx/html
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80 443
