//
//  Rest.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 22/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import Foundation

enum ImageError {
    case invalidJSON
    case url
    case noResponse
    case noData
    case httpError(code: Int)
}

class REST {
    
    static let basePath = "https://api.thecatapi.com/v1/images/"
    
    //https://api.thecatapi.com/v1/images/{image_id}
    
    //https://api.thecatapi.com/v1/images/search?limit=5&page=10&order=Desc
    
    //static let session = URLSession.shared
    static let configuration: URLSessionConfiguration = {
        let configuration = URLSessionConfiguration.default
        configuration.httpAdditionalHeaders = ["Content-Type": "application/json", "x-api-key" : "aaae9270-88f4-4e04-a09c-fc4f48037155"]
        configuration.timeoutIntervalForResource = 10.0
        configuration.allowsCellularAccess = false
        configuration.httpMaximumConnectionsPerHost = 5
        return configuration
    }()
    
    static let session = URLSession(configuration: configuration)
    
    class func load(page: Int, onComplete: @escaping ([CatImage])->Void, onError: @escaping (ImageError)->Void ){
        
        let path = "\(basePath)search?limit=10&page=\(page)&order=Desc"
        guard let url = URL(string: path) else {
            return onError(.url)
        }
        let task = session.dataTask(with: url) { (data, response, error) in
            if error != nil {
                return onError(.noResponse)
            } else {
                guard let response = response as? HTTPURLResponse else {
                    return onError(.noResponse)
                }
                switch response.statusCode {
                case 200...299:
                    guard let data = data else {
                        return onError(.noData)
                    }
                    do {
                        let cars = try JSONDecoder().decode([CatImage].self, from: data)
                        onComplete(cars)
                    } catch {
                        return onError(.invalidJSON)
                    }
                default:
                    return onError(.httpError(code: response.statusCode))
                }
            }
        }
        task.resume()
    }
    
    class func request(imageObj: CatImage, operation: Operation, onComplete: @escaping (Bool)->Void) -> Void /* URLSessionDataTask? */ {
        let path = basePath + "/" + imageObj.id
        let httpMethod = operation.rawValue
        
        guard let url = URL(string: path) else {
            return onComplete(false)
            //return nil
        }
        
        var request = URLRequest(url: url)
        request.httpMethod = httpMethod
        
        let data = try? JSONEncoder().encode(imageObj)
        request.httpBody = data
        
        let task = session.dataTask(with: request) { (data, response, error) in
            if error != nil {
                return onComplete(false)
                //return nil
            }
            
            guard let response = response as? HTTPURLResponse, response.statusCode == 200, let _ = data else {
                return onComplete(false)
                //return nil
            }
            
            onComplete(true)
        }
        task.resume()
        //return task
        
    }
    
}


enum Operation: String {
    case load = "GET"
    case update = "PUT"
    case delete = "DELETE"
    case create = "POST"
}
