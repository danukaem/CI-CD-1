# Use an official Node.js image to build the app
FROM node:18 AS build

WORKDIR /app

# Copy package files and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of the application source code
COPY . .

# Build the application
# RUN npm run build

ARG CONFIGURATION=production
RUN npm run build -- --configuration=$CONFIGURATION


# Use an Nginx image to serve the built app
FROM nginx:alpine

# Copy built files from the previous stage
COPY --from=build /app/dist/demo-frontend /usr/share/nginx/html

# Copy custom Nginx config if needed (optional)
# COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]