//
//  UIViewController+CoreData.swift
//  DesafioTechFit
//
//  Created by Bruno Garcia on 27/02/19.
//  Copyright © 2019 Bruno Garcia. All rights reserved.
//

import Foundation
import UIKit
import CoreData

extension UIViewController {
    var appDelegate: AppDelegate {
        return UIApplication.shared.delegate as! AppDelegate
    }
    
    var context: NSManagedObjectContext {
        return appDelegate.persistentContainer.viewContext
    }
    
    func saveContext() {
        if context.hasChanges {
            do {
                try context.save()
            } catch {
                print("Erro ao salvar contexto:", error)
            }
        }
    }
}
